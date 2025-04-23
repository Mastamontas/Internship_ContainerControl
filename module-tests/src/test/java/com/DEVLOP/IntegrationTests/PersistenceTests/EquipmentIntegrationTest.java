package com.DEVLOP.IntegrationTests.PersistenceTests;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Repositories.EquipmentRepo;
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
public class EquipmentIntegrationTest {

    @LocalServerPort
    private int port;
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");
    @Autowired
    private EquipmentRepo equipment;
    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

    @BeforeEach
    public void setUp(){
        equipment.DeleteAllEquipments();
        IntStream.rangeClosed(1,3).forEach(i->{
            com.DEVLOP.Entities.Equipment equipment = EquipmentFactory.CreateEquipment();
            //repensar agrupar funções para persistencia de tipos de equipamentos
            this.equipment.PersistEquipmentClass(equipment.getEquipmentType().getEquipmentClass());
            this.equipment.PersistEquipmentType(equipment.getEquipmentType());
            this.equipment.PersistEquipment(equipment);
        });
    }

    @Test
    public void GetByIDTest(){
        //arrange
        com.DEVLOP.Entities.Equipment eq = equipment.FindAll().stream().findFirst()
                .orElseThrow(() -> new EquipmentNotFoundException("No equipment found"));

        //act
        int generatedId = eq.getId();
        Optional<com.DEVLOP.Entities.Equipment> retrievedEq = equipment.FindByID(generatedId);

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
        List<com.DEVLOP.Entities.Equipment> eqList =  equipment.FindAll();
        eqList.forEach(System.out::println);

        assertNotNull(eqList, "List should not be null");
        assertFalse(eqList.isEmpty(), "list is not empty");

    }

    @Test
    public void EquipmentUpdatedSuccessfullyTest(){
        //arrange
        com.DEVLOP.Entities.Equipment eq = equipment.FindAll().stream().findFirst().orElseThrow(()-> new EquipmentNotFoundException(
                "equipments not found"
        ));
        //act
        String oldPrefix = eq.getPrefix();
        String alteration = "AAAA";
        eq.setPrefix(alteration);
        equipment.UpdateEquipment(eq);
        com.DEVLOP.Entities.Equipment updatedEquipment = equipment.FindByID(eq.getId()).orElseThrow();
        //assert
        assertNotNull(updatedEquipment, "updated equipment should not be null");
        assertEquals(alteration, updatedEquipment.getPrefix(), "new prefixes should match");
        assertTrue(updatedEquipment.getPrefix().matches(alteration));
        assertNotEquals(oldPrefix, updatedEquipment.getPrefix(), "equipment prefixes should not match");
    }
    @Test
    public void DeleteAllEquipmentsTest(){
        equipment.DeleteAllEquipments();
        assertThrows(EquipmentNotFoundException.class,
                () -> equipment.FindByID(1).orElseThrow(() -> new EquipmentNotFoundException("Equipment not found"))
        );
    }
}
