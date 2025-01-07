package com.quackcoders.Pipeline.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private final String SECRET_KEY = "Woah1This2Is3For4The5Secret6Key7And8Were9Gonna10Have11Random12StuffHere";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate JWT token with username and role
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setClaims(Map.of("role", role.toUpperCase())) // Add role to claims
                .setSubject(username) // Set the username (email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issue date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration (10 hours)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign the token
                .compact();
    }

    // Extract the username (email) from the token
    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            System.out.println("Token: " + token);
            System.out.println("Secret Key: " + SECRET_KEY);
            throw e;
        }
    }

    // Extract the role from the token
    public String extractRole(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

    // Validate the token by comparing the username and checking expiration
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
