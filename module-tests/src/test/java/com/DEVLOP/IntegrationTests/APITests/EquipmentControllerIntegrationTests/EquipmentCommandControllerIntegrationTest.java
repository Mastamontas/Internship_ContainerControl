package com.DEVLOP.IntegrationTests.APITests.EquipmentControllerIntegrationTests;



import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Application.Mappers.IEquipmentMapperImpl;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Repositories.EquipmentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentCommandControllerIntegrationTest {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource //testcontainer setup
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IEquipmentMapperImpl mapper;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private ObjectMapper objectMapper;

    private Equipment equipment;
    private EquipmentDto equipmentDTO;
    @BeforeEach
    public void setUp() {
        equipment = EquipmentFactory.CreateEquipment();
        equipmentRepo.PersistEquipmentClass(equipment.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(equipment.getEquipmentType());
        equipment = equipmentRepo.PersistEquipment(equipment); // Save to the database
        equipmentDTO = mapper.MaptoEquipmentDto(equipment);
    }

    @Test
    public void UpdateEquipment_validInput_updatesDatabase() throws Exception {
        //arrange
        int id = equipment.getId();
        equipmentDTO.setComment("Updated Comment");

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipments/{id}/update", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(equipmentDTO)))
                .andExpect(request().asyncStarted()) // Verify async started
                .andReturn(); // Capture the MvcResult


        mockMvc.perform(asyncDispatch(mvcResult)) // Dispatch asynchronously
                .andExpect(status().isOk())
                .andExpect(content().string("Equipment was updated successfully"));

        //assert
        com.DEVLOP.Entities.Equipment updatedEquipment = equipmentRepo.FindByID(id).orElse(null);
        assert updatedEquipment != null;
        assertEquals("Updated Comment", updatedEquipment.getComment());
    }
    /*
    falta teste de excepcao caso corra mal
     */
}
