package com.DEVLOP.IntegrationTests.APITests.EquipmentServiceControllersIntegrationTests;


import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Factories.EquipmentClassFactory;
import com.DEVLOP.Factories.EquipmentServiceFactory;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import com.DEVLOP.Repositories.EquipmentServiceRepo;
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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentServiceQueryControllerIntegrationTest {
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
    private EquipmentServiceRepo equipmentServiceRepo;

    @BeforeEach
    public void SetUp(){
        IntStream.rangeClosed(1,10).forEach(i->{
            EquipmentService equipmentService = EquipmentServiceFactory.CreateEquipmentService();
            equipmentServiceRepo.PersistEquipmentService(equipmentService);
        });
    }


    @Test
    public void GetEquipmentServiceByID() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipmentService/{id}",1))
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
    public void GetEquipmentServiceByCode() throws Exception {


        EquipmentService equipmentService = equipmentServiceRepo.ReturnEquipmentServiceByID(1).orElseThrow();
        String code = equipmentService.getEquipmentServiceCode();
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipmentService/code/{code}",code))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equipmentServiceCode").value(code))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
    @Test
    public void GetEquipmentServiceByIDList() throws Exception {
        // Arrange
        List<Integer> idList = Arrays.asList(1, 5, 7);

        // Convert idList to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(idList);

        // Act
        MvcResult mvcResult = mockMvc.perform(post("/v1/equipmentService/batch")
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
