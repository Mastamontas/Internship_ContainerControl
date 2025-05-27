package com.DEVLOP.IntegrationTests.APITests.EquipmentClassControllersIntegrationTests;

import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Factories.EquipmentClassFactory;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentClassCommandControllerIntegrationTest {

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
    private EquipmentClassRepo equipmentClassRepo;

    @Test
    public void CreateNewEquipmentClass_Test() throws Exception {
        //arrange
        EquipmentClassDto equipmentClassDto = new EquipmentClassDto();
        equipmentClassDto.setEquipmentClassCode("AAA");
        equipmentClassDto.setEquipmentClassName("Test");
        equipmentClassDto.setEquipmentClassType("Test type");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentClassDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/equipmentClass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.equipmentClassCode").value("AAA"))
                .andExpect(jsonPath("$.equipmentClassName").value("Test"));
    }
    @Test
    public void UpdateNewEquipmentClass_Test() throws Exception {
        //arrange
        EquipmentClass equipmentClass = EquipmentClassFactory.CreateEquipmentClass();
        equipmentClassRepo.PersistEquipmentClass(equipmentClass);

        EquipmentClassDto equipmentClassDto = new EquipmentClassDto();
        equipmentClassDto.setId(1); //funciona porque o ID é o da entidade acima gravada. mas este teste nao é estável
        equipmentClassDto.setEquipmentClassCode("AAA");
        equipmentClassDto.setEquipmentClassName("Test");
        equipmentClassDto.setEquipmentClassType("Test type");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentClassDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipmentClass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.equipmentClassCode").value("AAA"))
                .andExpect(jsonPath("$.equipmentClassName").value("Test"));

    }
}
