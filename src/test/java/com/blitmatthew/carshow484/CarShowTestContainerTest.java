package com.blitmatthew.carshow484;

import com.blitmatthew.carshow484.controller.CarController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.ContainerLaunchException;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CarShowTestContainerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CarController carController;

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"))
            .withExposedPorts(3306, 3322)
            .withDatabaseName("carshow")
            .withUsername("carshowdb")
            .withPassword("carshowdb");

    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        try {
            mysqlContainer.start();
        } catch (ContainerLaunchException e) {
            System.out.println("Container failed to start. Here are the logs:");
            System.out.println(mysqlContainer.getLogs());
            throw e; // Re-throw the exception if you need to
        }
    }

    @AfterAll
    public static void afterAll() throws Exception {
        mysqlContainer.stop();
    }

    @Test
    public void testCarShow() {
        jdbcTemplate.execute("USE carshow");

        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM car", Integer.class);
        assertEquals(3, count);
    }
}
