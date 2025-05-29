package com.DEVLOP.IntegrationTests.APITests.EquipmentClassControllersIntegrationTests;


import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Factories.EquipmentClassFactory;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentClassQueryControllerIntegrationTest {
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

    @BeforeEach
    public void SetUp(){
        IntStream.rangeClosed(1,10).forEach(i->{
            EquipmentClass equipmentClass = EquipmentClassFactory.CreateEquipmentClass();
            equipmentClassRepo.PersistEquipmentClass(equipmentClass);
        });
    }


    @Test
    public void GetEquipmentClassByID() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipmentClass/{id}",1))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void GetEquipmentClassByCode() throws Exception {


        EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByID(1).orElseThrow();
        String code = equipmentClass.getEquipmentClassCode();
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipmentClass/code/{code}",code))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equipmentClassCode").value(code))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
    @Test
    public void GetEquipmentClassByIDList() throws Exception {
        // Arrange
        List<Integer> idList = Arrays.asList(1, 5, 7);

        // Convert idList to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(idList);

        // Act
        MvcResult mvcResult = mockMvc.perform(post("/v1/equipmentClass/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        // Assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3)) // Expecting a list of 3 items
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(5))
                .andExpect(jsonPath("$[2].id").value(7));
    }



}
