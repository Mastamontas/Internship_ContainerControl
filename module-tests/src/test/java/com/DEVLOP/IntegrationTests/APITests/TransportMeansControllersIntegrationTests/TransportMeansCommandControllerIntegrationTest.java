package com.DEVLOP.IntegrationTests.APITests.TransportMeansControllersIntegrationTests;


import com.DEVLOP.Application.DTOS.TransportMeansDto;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Entities.TransportMeans;
import com.DEVLOP.Repositories.TransportMeansRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class TransportMeansCommandControllerIntegrationTest {

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
    private TransportMeansRepo transportMeansRepo;

    @Test
    public void CreateNewTransportMeans_Test() throws Exception{

        TransportMeansDto transportMeansDto = new TransportMeansDto();
        transportMeansDto.setComment("Created");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(transportMeansDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/transportMeans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("Created"));

        TransportMeans transportMeans = transportMeansRepo.FindTransportMeansByID(1).orElseThrow();
        assertEquals(1, transportMeans.getId());
        assertEquals("Created", transportMeans.getComment());
    }

    @Test
    public void UpdateTransportMeans_Test() throws Exception{
        TransportMeans transportMeans = new TransportMeans();
        transportMeans.setComment("Comment");
        transportMeansRepo.PersistTransportMeans(transportMeans);

        TransportMeansDto transportMeansDto = new TransportMeansDto();
        transportMeansDto.setId(1);
        transportMeansDto.setComment("Updated");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(transportMeansDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/transportMeans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comment").value("Updated"));


    }
}
