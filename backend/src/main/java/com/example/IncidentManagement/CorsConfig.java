package com.example.IncidentManagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Configuration class to enable Cross-Origin Resource Sharing (CORS)
 * for the Spring Boot application.
 */
@Configuration
public class CorsConfig {

    /**
     * This method defines a CORS filter that allows specific cross-origin requests.
     * The filter is registered as a Spring bean.
     *
     * @return CorsFilter instance that applies the defined CORS policy.
     */
    @Bean
    public CorsFilter corsFilter() {
        // Create an instance of CorsConfiguration to configure CORS policy
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Add allowed origins (frontend URLs). Replace with actual production URL.
        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000", // React development environment
                "https://your-production-url.com" // Production environment URL
        ));

        // Allow all HTTP methods (GET, POST, PUT, DELETE, OPTIONS)
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allow all headers in requests
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        // Allow credentials (e.g., cookies, authorization tokens) in cross-origin requests
        corsConfiguration.setAllowCredentials(true);

        // Set the maximum age for preflight requests (to reduce repeated preflight requests)
        corsConfiguration.setMaxAge(3600L);

        // Create a source that will manage multiple CORS configurations
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Register the CORS configuration for all endpoints ("/**")
        source.registerCorsConfiguration("/**", corsConfiguration);

        // Return a new instance of CorsFilter with the given source
        return new CorsFilter(source);
    }
}
