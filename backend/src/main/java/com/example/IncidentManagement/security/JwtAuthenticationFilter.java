package com.example.IncidentManagement.security;

import com.example.IncidentManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the token from the header

            try {
                // Validate the token
                if (jwtUtil.validateToken(token)) {
                    // Extract the username and roles from the token
                    String username = jwtUtil.extractUsername(token);
                    List<String> roles = jwtUtil.extractRoles(token);

                    // Convert roles to SimpleGrantedAuthority
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    // Create the authentication token
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);

                    // Set the authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    // Invalid token
                    logger.error("Invalid JWT token.");
                    sendErrorResponse(response, "Invalid JWT token.");
                    return;
                }
            } catch (ExpiredJwtException e) {
                // Handle expired JWT token
                logger.error("Expired JWT token.");
                sendErrorResponse(response, "Expired JWT token.");
                return;
            } catch (MalformedJwtException e) {
                // Handle malformed JWT token
                logger.error("Malformed JWT token.");
                sendErrorResponse(response, "Malformed JWT token.");
                return;
            } catch (Exception e) {
                // Handle other exceptions
                logger.error("Error processing JWT token.", e);
                sendErrorResponse(response, "Invalid or expired JWT token.");
                return;
            }
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * Sends an error response to the client with the provided message.
     *
     * @param response The HttpServletResponse object.
     * @param message  The error message to be sent.
     * @throws IOException If an I/O error occurs while writing the response.
     */
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set the HTTP status to 401 (Unauthorized)
        response.setContentType("application/json"); // Set content type to JSON
        response.getWriter().write("{\"error\": \"" + message + "\"}"); // Write the error message in JSON format
    }
}
