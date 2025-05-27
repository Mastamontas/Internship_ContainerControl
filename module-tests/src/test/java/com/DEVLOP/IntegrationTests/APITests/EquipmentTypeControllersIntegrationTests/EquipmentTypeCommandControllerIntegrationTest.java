package com.DEVLOP.IntegrationTests.APITests.EquipmentTypeControllersIntegrationTests;

import com.DEVLOP.Application.DTOS.EquipmentTypeDto;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Factories.EquipmentClassFactory;
import com.DEVLOP.Factories.EquipmentTypeFactory;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import com.DEVLOP.Repositories.EquipmentTypeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentTypeCommandControllerIntegrationTest {
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
    private EquipmentTypeRepo equipmentTypeRepo;
    @Autowired
    private EquipmentClassRepo equipmentClassRepo;


    @BeforeEach
    public void SetUp(){
        EquipmentClass equipmentClass = EquipmentClassFactory.CreateEquipmentClass();
        equipmentClassRepo.PersistEquipmentClass(equipmentClass);
    }
    @Test
    public void CreateNewEquipmentType_Test() throws Exception{
        //arrange - there needs to exist an equipment class for the creation of an equipment type
        EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByID(1).orElseThrow();
        EquipmentTypeDto equipmentTypeDto = new EquipmentTypeDto();
        equipmentTypeDto.setEquipmentClassID(equipmentClass.getId());
        equipmentTypeDto.setEquipmentTypeCode("AAA");
        equipmentTypeDto.setEquipmentTypeName("Test");
        equipmentTypeDto.setEquipmentTypeHeight(2.05);
        equipmentTypeDto.setEquipmentTypeHeight(2.05);


        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentTypeDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/equipmentType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.equipmentTypeCode").value("AAA"))
                .andExpect(jsonPath("$.equipmentTypeName").value("Test"));

        EquipmentType equipmentType = equipmentTypeRepo.ReturnEquipmentTypeByID(1).orElseThrow();
        assertEquals(1, equipmentType.getId());
        assertEquals("AAA", equipmentType.getEquipmentTypeCode());
        assertEquals(1, equipmentClass.getId());
    }

    @Test
    public void UpdateEquipmentType_Test() throws Exception{

        //cria nova equipment class para atualizar no tipo
        EquipmentClass equipmentClass = EquipmentClassFactory.CreateEquipmentClass();
        equipmentClassRepo.PersistEquipmentClass(equipmentClass);

        EquipmentType equipmentType = EquipmentTypeFactory.CreateEquipmentType(equipmentClassRepo.ReturnEquipmentClassByID(1)
                .orElseThrow());
        equipmentTypeRepo.PersistEquipmentType(equipmentType);

        //verifica se esta entidade existe
        EquipmentType equipmentTypeToUpdate = equipmentTypeRepo.ReturnEquipmentTypeByID(1).orElseThrow();


        //old data to update
        String oldCode = equipmentTypeToUpdate.getEquipmentTypeCode();
        String oldName = equipmentTypeToUpdate.getEquipmentTypeName();
        Integer oldEquipmentClassID = equipmentTypeToUpdate.getEquipmentClass().getId();
        String oldEquipmentClassCode = equipmentTypeToUpdate.getEquipmentClass().getEquipmentClassCode();

        //new Data to add
        String newCode = "new code";
        String newName = "new name";

        //data to update
        EquipmentTypeDto equipmentTypeDto = new EquipmentTypeDto();
        equipmentTypeDto.setId(equipmentTypeToUpdate.getId());
        equipmentTypeDto.setEquipmentClassID(2); //created above
        equipmentTypeDto.setEquipmentTypeCode(newCode);
        equipmentTypeDto.setEquipmentTypeName(newName);
        equipmentTypeDto.setEquipmentTypeHeight(2.10);
        equipmentTypeDto.setEquipmentTypeHeight(2.10);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentTypeDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipmentType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(equipmentTypeToUpdate.getId()))
                .andExpect(jsonPath("$.equipmentTypeCode").value(newCode))
                .andExpect(jsonPath("$.equipmentTypeName").value(newName))
                .andExpect(jsonPath("$.equipmentTypeCode").value(org.hamcrest.Matchers.not(oldCode)))
                .andExpect(jsonPath("$.equipmentTypeName").value(org.hamcrest.Matchers.not(oldName)));

        EquipmentType equipmentTypeUpdated = equipmentTypeRepo.ReturnEquipmentTypeByID(equipmentTypeToUpdate.getId()).orElseThrow();
        assertEquals(equipmentTypeUpdated.getEquipmentClass().getId(), equipmentClass.getId());
        assertNotEquals(equipmentTypeUpdated.getEquipmentClass().getEquipmentClassCode(), oldEquipmentClassCode);
    }
}
