package com.DEVLOP.IntegrationTests.APITests;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.stream.IntStream;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/*
replace create equipment methods with factory methods
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentQueryControllerIntegrationTest {


    @LocalServerPort //isto é necessário aqui?
    private int port;
    @Container
    private static final MySQLContainer <?> mysql = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource //testcontainer setup
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        equipmentRepository.DeleteAllEquipments();
        IntStream.rangeClosed(1,3).forEach(i->{
            Equipment equipment = EquipmentFactory.CreateEquipment();
            equipmentRepository.PersistEquipmentClass(equipment.getEquipmentType().getEquipmentClass());
            equipmentRepository.PersistEquipmentType(equipment.getEquipmentType());
            equipmentRepository.PersistEquipment(equipment);
        });
    }

    @Test
    public void testGetAllEquipmentsAsync() throws Exception {
        //act
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipments/"))
                .andExpect(request().asyncStarted())
                .andReturn();
        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    /*
    completar no proximo branch: adicao de filtros à pesquisa
     */
    @Test
    public void testGetEquipmentByUniqueDetails() throws Exception {
    }

    @Test
    public void testGetEquipmentByIdAsync() throws Exception {
        //setup
        List<Equipment> eqList = equipmentRepository.FindAll();
        Equipment eq = eqList.get(0);
        //act
        MvcResult mvcResult = mockMvc.perform(get("/v1/equipments/{id}", eq.getId()))
                .andExpect(request().asyncStarted())
                .andReturn();
        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eq.getId()))
                .andExpect(jsonPath("$.prefix").value(eq.getPrefix()))
                .andExpect(jsonPath("$.number").value(eq.getNumber()));
    }
}
