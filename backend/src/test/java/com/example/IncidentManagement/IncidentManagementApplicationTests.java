package com.example.IncidentManagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for verifying the Spring Boot application context loading.
 */
@SpringBootTest
public class IncidentManagementApplicationTests {

    /**
     * This test checks whether the Spring Boot application context loads successfully.
     * 
     * The test will pass if the application context loads without any issues,
     * and fail if there are any problems with the Spring Boot configuration.
     */
    @Test
    void contextLoads() {
        // The test will simply check if the context loads properly.
        // If there are any issues with your beans or configurations,
        // Spring will throw an exception during context initialization, and the test will fail.
    }
}
