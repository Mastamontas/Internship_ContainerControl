package com.DEVLOP.IntegrationTests.PersistenceTests;

import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;


@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovementRepositoryIntegrationTest {


    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private MovementRepository movementRepository;

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    /*
    setup

    criar um equipamento
    criar 3 movimentos

    testar retorno lista, criação movimento, apagar movimentos
     */
    @BeforeEach
    public void SetUp(){
        Equipment testEquipment = EquipmentFactory.CreateEquipment();
        //mudar nome metodo para persistEquipment
        equipmentRepository.SaveEquipmentClassInDb(testEquipment.getEquipmentType().getEquipmentClass());
        equipmentRepository.SaveEquipmentTypeInDb(testEquipment.getEquipmentType());
        equipmentRepository.SaveEquipmentInDb(testEquipment);//PersistEquipment (todo:change method name)
        IntStream.rangeClosed(1,4).forEach(i->{
            Movement testMovement = MovementFactory.CreateMovementEntity(testEquipment);
            movementRepository.PersistMovement(testMovement);
        });
    }
    @Test //todo:refactor or delete method
    public void FindAMovement(){
        List<Movement> movements = movementRepository.FindAllMovements();
        for (Movement m : movements){
            System.out.println(m.getDate());
            System.out.println(m.getEquipment().getId());
            System.out.println(m.getEquipment().getPrefix());
        }

    }

    @Test
    public void ReturnsListByDateOrder(){
        //setup ir buscar equipamento com id 3
        Equipment eq = equipmentRepository.FindAll().stream().findFirst().orElseThrow(()-> new EquipmentNotFoundException("no equip in test"));
        List<Movement> orderedMovementList = movementRepository.GetMovementsOfEquipment(eq);
        for (Movement m : orderedMovementList){
            System.out.println(m.getDate());
            System.out.println(m.getEquipment().getId());
            System.out.println(m.getEquipment().getPrefix());
        }

    }

}

