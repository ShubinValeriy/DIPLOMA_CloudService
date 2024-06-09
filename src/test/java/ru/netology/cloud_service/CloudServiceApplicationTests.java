package ru.netology.cloud_service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CloudServiceApplicationTests {
    private static final int PORT_DB = 5432;
    private static final int PORT_SERVER = 5555;
    private static final String DB_NAME = "cloud_db_new";
    private static final String DB_USERNAME = "v.shubin";
    private static final String DB_PASSWORD = "Bnk53lfq";


    @Container
    public static PostgreSQLContainer<?> dBContainer = new PostgreSQLContainer<>("postgres")
            .withExposedPorts(PORT_DB)
            .withDatabaseName(DB_NAME)
            .withUsername(DB_USERNAME)
            .withPassword(DB_PASSWORD);

    @Container
    public static GenericContainer<?> cloudApp = new GenericContainer<>("cloud_app:latest")
            .withExposedPorts(PORT_SERVER)
            .withEnv(Map.of("SPRING_DATASOURCE_URL", "jdbc:postgresql://cloudApp:" + PORT_DB + "/" + DB_NAME))
            .dependsOn(dBContainer);

    @Test
    void contextDatabase() {
        Assertions.assertTrue(dBContainer.isRunning());
    }

    @Test
    void contextServer() {
        Assertions.assertFalse(cloudApp.isRunning());
    }
}