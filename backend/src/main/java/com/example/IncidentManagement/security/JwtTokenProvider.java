package com.example.IncidentManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenProvider {

    private final String secretKey = "your-secret-key";  // Secret key for JWT

    /**
     * Generate JWT token
     * @param username The username to be included in the token's claims
     * @return The generated JWT token
     */
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);  // Set subject (username)
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);  // 1 hour expiration time

        // Create signing key using the secret key
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setClaims(claims)  // Set the claims
                .setIssuedAt(now)  // Set issued date
                .setExpiration(validity)  // Set expiration time
                .signWith(key)  // Sign with the secret key
                .compact();  // Return the compact JWT token
    }

    /**
     * Extract token from request header
     * @param request The HTTP request
     * @return The JWT token, or null if not found
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Extract token without "Bearer " prefix
        }
        return null;
    }

    /**
     * Validate JWT token
     * @param token The JWT token to validate
     * @return True if the token is valid, otherwise false
     */
    public boolean validateToken(String token) {
        try {
            // Create signing key using the secret key
            Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

            // Parse the claims from the token using the signing key
            Jwts.parserBuilder()
                .setSigningKey(key)  // Set the signing key
                .build()  // Build the parser
                .parseClaimsJws(token);  // Parse the token claims

            return true;  // Token is valid
        } catch (Exception e) {
            return false;  // If any exception occurs (e.g., token expired or malformed), return false
        }
    }

    /**
     * Get authentication from the JWT token
     * @param token The JWT token
     * @return Authentication object
     */
    public Authentication getAuthentication(String token) {
        String username = getUsername(token);  // Get the username from the token
        User principal = new User(username, "", null);  // Create a basic User object (no password, roles not included)
        return new UsernamePasswordAuthenticationToken(principal, token, null);  // Return authentication token
    }

    /**
     * Extract username from JWT token
     * @param token The JWT token
     * @return The username (subject) extracted from the token
     */
    private String getUsername(String token) {
        // Create signing key using the secret key
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        // Parse the claims from the token using the signing key
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)  // Set the signing key
                .build()  // Build the parser
                .parseClaimsJws(token)  // Parse the claims
                .getBody();  // Get the claims body

        return claims.getSubject();  // Return the subject (username) from the claims
    }
}
