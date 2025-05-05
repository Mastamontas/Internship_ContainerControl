package com.DEVLOP.IntegrationTests.APITests;

import com.DEVLOP.Entities.*;
import com.DEVLOP.Factories.*;
import com.DEVLOP.Repositories.*;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class MovementQueryControllerIntegrationTest {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource //testcontainer setup
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

    @Autowired
    private MovementRepo movementRepo;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private MovementTypeRepo movementTypeRepo;
    @Autowired
    private EquipmentServiceRepo equipmentServiceRepo;
    @Autowired
    private EquipmentLeasingRepo equipmentLeasingRepo;
    @Autowired
    private EquipmentConditionRepo equipmentConditionRepo;
    @Autowired
    private EquipmentStatusRepo equipmentStatusRepo;
    @Autowired
    private TransportMeansRepo transportMeansRepo;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void SetUp(){
        com.DEVLOP.Entities.Equipment testEquipment = EquipmentFactory.CreateEquipment();
        equipmentRepo.PersistEquipmentClass(testEquipment.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(testEquipment.getEquipmentType());
        equipmentRepo.PersistEquipment(testEquipment);
        /*
        estes testes nao passam porque é necessário criar entidades para todos os elementos
         */
        //-----Nested entities of movement
        MovementType moveType = MovementTypeFactory.CreateMovementType();
        movementTypeRepo.PersistMovementType(moveType);

        EquipmentCondition equipCondition = EquipmentConditionFactory.CreateEquipmentCondition();
        equipmentConditionRepo.PersistEquipmentCondition(equipCondition);

        EquipmentStatus equipStatus = EquipmentStatusFactory.CreateEquipmentStatus();
        equipmentStatusRepo.PersistEquipmentStatus(equipStatus);

        EquipmentService equipService = EquipmentServiceFactory.CreateEquipmentService();
        equipmentServiceRepo.PersistEquipmentService(equipService);

        EquipmentLeasing equipLeasing = EquipmentLeasingFactory.CreateEquipmentLeasing();
        equipmentLeasingRepo.PersistEquipmentLeasing(equipLeasing);

        TransportMeans transportMeans = new TransportMeans();
        transportMeansRepo.PersistTransportMeans(transportMeans);

        IntStream.rangeClosed(1,25).forEach(i->{
            com.DEVLOP.Entities.Movement testMovement = MovementFactory.CreateMovement(testEquipment);

            testMovement.setMovementType(moveType);
            testMovement.setEquipmentStatus(equipStatus);
            testMovement.setEquipmentCondition(equipCondition);
            testMovement.setEquipmentService(equipService);
            testMovement.setEquipmentLeasing(equipLeasing);
            testMovement.setTransportMeans(transportMeans);

            movementRepo.PersistMovement(testMovement);
            System.out.println(testMovement.getEquipmentStatus().getEquipmentStatusCode());
        });
    }

    /*
    todo: get por equipamento pela matricula (prefixo, number, check digit) campos obrigatorios
     */
    @Test
    public void GetMovementsOfEquipment() throws Exception{
        //act
        MvcResult mvcResult = mockMvc.perform(get("/v1/movements/equipment/{id}",1))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void GetFilteredMovementsTest() throws Exception{

        MvcResult mvcResult = mockMvc.perform(get("/v1/movements/filter")
                        .param("equipmentStatus.equipmentStatusCode", "IN_PROGRESS"))
                .andExpect(request().asyncStarted())
                .andReturn();

        // Assert: Validate response
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void RangeMovementTest() throws Exception{
        /*
        receber duas datas e retorna lista de movimentos entre essas datas
        setup de movimentos tem de criar várias datas
         */
        MvcResult mvcResult = mockMvc.perform(get("/v1/movements/rangeFilter")
                        .param("dateFrom", "2025-05-01")
                        .param("dateTo", "2025-05-05"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
