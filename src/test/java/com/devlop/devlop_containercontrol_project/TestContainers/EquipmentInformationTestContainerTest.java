package com.DEVLOP.devlop_containercontrol_project.TestContainers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import static org.junit.jupiter.api.Assertions.assertTrue;



//esta classe é abstrata para poder ser extendida por outras classes de teste

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EquipmentInformationTestContainerTest {


    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("localDB")
            .withUsername("testUsername")
            .withPassword("testpassword");
    //aqui pode se adicionar reuse strategy, network, etc
    //para limpar requer manualmente usar a CLI do docker

    @Container
    @ServiceConnection
    static MySQLContainer<?> externalDBContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("externalDB")
            .withUsername("testUsername")
            .withPassword("testpassword");
            //start script init.sql

    //kafka container
    //@Container
    //static KafkaContainer kafkaContainer = new KafkaContainer();
    @Test
    void assertContainerIsRunning() {
        assertTrue(mySQLContainer.isCreated());
        assertTrue(mySQLContainer.isRunning());
    }

}
