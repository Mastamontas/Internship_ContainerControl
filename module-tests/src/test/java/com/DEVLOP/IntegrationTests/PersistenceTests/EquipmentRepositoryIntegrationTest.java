package com.DEVLOP.IntegrationTests.PersistenceTests;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
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
import java.util.Optional;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EquipmentRepositoryIntegrationTest {

    @LocalServerPort
    private int port;
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");
    @Autowired
    private EquipmentRepository equipmentRepository;
    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

    @BeforeEach
    public void setUp(){
        equipmentRepository.DeleteAllEquipments();
        IntStream.rangeClosed(1,3).forEach(i->{
            Equipment equipment = EquipmentFactory.CreateEquipment();
            //repensar agrupar funções para persistencia de tipos de equipamentos
            equipmentRepository.PersistEquipmentClass(equipment.getEquipmentType().getEquipmentClass());
            equipmentRepository.PersistEquipmentType(equipment.getEquipmentType());
            equipmentRepository.PersistEquipment(equipment);
        });
    }

    @Test
    public void GetByIDTest(){
        //arrange
        Equipment eq = equipmentRepository.FindAll().stream().findFirst()
                .orElseThrow(() -> new EquipmentNotFoundException("No equipment found"));

        //act
        int generatedId = eq.getId();
        Optional<Equipment> retrievedEq = equipmentRepository.FindByID(generatedId);

        //assert
        assertTrue(retrievedEq.isPresent());
        assertNotNull(retrievedEq, "Equipment should not be null");
        assertNotNull(retrievedEq.get().getEquipmentType(), "Equipment type not null on equipment");
        assertNotNull(retrievedEq.get().getEquipmentType().getEquipmentClass(), "equipment class of equipment type" +
                "should not be null");
        assertEquals(generatedId, retrievedEq.get().getId(), "Equipment ID should match the one retrieved from DB");
    }

    @Test
    public void FindAllEquipmentsTest(){
        List<Equipment> eqList =  equipmentRepository.FindAll();
        eqList.forEach(System.out::println);

        assertNotNull(eqList, "List should not be null");
        assertFalse(eqList.isEmpty(), "list is not empty");

    }

    @Test
    public void EquipmentUpdatedSuccessfullyTest(){
        //arrange
        Equipment eq = equipmentRepository.FindAll().stream().findFirst().orElseThrow(()-> new EquipmentNotFoundException(
                "equipments not found"
        ));
        //act
        String oldPrefix = eq.getPrefix();
        String alteration = "AAAA";
        eq.setPrefix(alteration);
        equipmentRepository.UpdateEquipment(eq);
        Equipment updatedEquipment = equipmentRepository.FindByID(eq.getId()).orElseThrow();
        //assert
        assertNotNull(updatedEquipment, "updated equipment should not be null");
        assertEquals(alteration, updatedEquipment.getPrefix(), "new prefixes should match");
        assertTrue(updatedEquipment.getPrefix().matches(alteration));
        assertNotEquals(oldPrefix, updatedEquipment.getPrefix(), "equipment prefixes should not match");
    }
    @Test
    public void DeleteAllEquipmentsTest(){
        equipmentRepository.DeleteAllEquipments();
        assertThrows(EquipmentNotFoundException.class,
                () -> equipmentRepository.FindByID(1).orElseThrow(() -> new EquipmentNotFoundException("Equipment not found"))
        );
    }
}
