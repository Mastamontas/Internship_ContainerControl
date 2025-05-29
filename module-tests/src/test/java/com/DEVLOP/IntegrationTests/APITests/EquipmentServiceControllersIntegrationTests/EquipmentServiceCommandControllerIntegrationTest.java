package com.DEVLOP.IntegrationTests.APITests.EquipmentServiceControllersIntegrationTests;

import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
import com.DEVLOP.Application.DTOS.EquipmentServiceDto;
import com.DEVLOP.Entities.EquipmentLeasing;
import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Factories.EquipmentServiceFactory;
import com.DEVLOP.Repositories.EquipmentServiceRepo;
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
public class EquipmentServiceCommandControllerIntegrationTest {
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


    @Test
    public void CreateNewEquipmentStatus_Test() throws Exception{
        EquipmentServiceDto equipmentServiceDto = new EquipmentServiceDto();
        equipmentServiceDto.setEquipmentServiceCode("AAA");
        equipmentServiceDto.setEquipmentServiceName("Test");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentServiceDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/equipmentService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.equipmentServiceCode").value("AAA"))
                .andExpect(jsonPath("$.equipmentServiceName").value("Test"));

        EquipmentService equipmentService = equipmentServiceRepo.ReturnEquipmentServiceByCode("AAA").orElseThrow();
        assertEquals(1, equipmentService.getId());
        assertEquals("AAA", equipmentService.getEquipmentServiceCode());

    }

    @Test
    public void UpdateEquipmentService_Test() throws Exception{

        EquipmentService equipmentService = EquipmentServiceFactory.CreateEquipmentService();
        equipmentServiceRepo.PersistEquipmentService(equipmentService);

        EquipmentService testEquipmentPersisted = equipmentServiceRepo.ReturnEquipmentServiceByID(1).orElseThrow();
        String oldCode = testEquipmentPersisted.getEquipmentServiceCode();
        String oldName = testEquipmentPersisted.getEquipmentServiceName();

        String newCode = "BBB";
        String newName = "Updated-Name";


        EquipmentServiceDto equipmentServiceDto = new EquipmentServiceDto();
        equipmentServiceDto.setId(testEquipmentPersisted.getId());
        equipmentServiceDto.setEquipmentServiceCode("BBB");
        equipmentServiceDto.setEquipmentServiceName("Updated-Name");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentServiceDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipmentService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testEquipmentPersisted.getId()))
                .andExpect(jsonPath("$.equipmentServiceCode").value(newCode))
                .andExpect(jsonPath("$.equipmentServiceName").value(newName))

                .andExpect(jsonPath("$.equipmentServiceCode").value(org.hamcrest.Matchers.not(oldCode)))
                .andExpect(jsonPath("$.equipmentServiceName").value(org.hamcrest.Matchers.not(oldName)));


        EquipmentService updatedEntity = equipmentServiceRepo.ReturnEquipmentServiceByID(testEquipmentPersisted.getId()).orElseThrow();
        assertEquals(newCode, updatedEntity.getEquipmentServiceCode());
        assertEquals(newName, updatedEntity.getEquipmentServiceName());

    }

}
