package com.example.IncidentManagement.config;

import com.example.IncidentManagement.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class to set up Spring Security for the Incident Management system.
 * It configures JWT authentication and disables CSRF for simplicity.
 */
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor injection for JwtAuthenticationFilter
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configures the SecurityFilterChain to handle HTTP security settings.
     * 
     * @param http The HttpSecurity object used to configure security settings.
     * @return Configured SecurityFilterChain bean.
     * @throws Exception If an error occurs during the security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF protection for stateless API
        http.csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/users/register", "/users/login").permitAll()  // Public access for these URLs
                .anyRequest().authenticated()  // All other URLs require authentication
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Add the JWT filter before the default authentication filter

            // Allow CORS for all endpoints
            .cors().and(); // Ensure CORS is enabled globally

        return http.build();  // Return the configured HttpSecurity object
    }

    /**
     * Provides an AuthenticationManager bean.
     * 
     * @param config AuthenticationConfiguration object used to create the AuthenticationManager.
     * @return AuthenticationManager bean.
     * @throws Exception If an error occurs while creating the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();  // Provide the AuthenticationManager bean
    }
}
