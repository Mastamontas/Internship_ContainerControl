package com.DEVLOP.IntegrationTests.APITests.EquipmentStatusControllersIntegrationTests;


import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentStatus;
import com.DEVLOP.Factories.EquipmentStatusFactory;
import com.DEVLOP.Repositories.EquipmentStatusRepo;
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

import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentStatusQueryControllerIntegrationTest {
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
    private EquipmentStatusRepo equipmentStatusRepo;

    @BeforeEach
    public void SetUp(){
        IntStream.rangeClosed(1,10).forEach(i->{
            EquipmentStatus equipmentStatus = EquipmentStatusFactory.CreateEquipmentStatus();
            equipmentStatusRepo.PersistEquipmentStatus(equipmentStatus);

        });
    }

    @Test
    public void GetEquipmentStatusByID() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipmentStatus/{id}",1))
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
    public void GetEquipmentStatusByCode() throws Exception {


        EquipmentStatus equipmentStatus = equipmentStatusRepo.ReturnEquipmentStatusByID(1).orElseThrow();
        String code = equipmentStatus.getEquipmentStatusCode();

        MvcResult mvcResult = mockMvc.perform(get("/v1/equipmentStatus/code/{code}",code))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equipmentStatusCode").value(code))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }


}
