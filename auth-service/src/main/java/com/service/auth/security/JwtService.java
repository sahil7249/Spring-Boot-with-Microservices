package com.service.auth.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.service.auth.entity.User;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(User user) {
        return Jwts.builder()
                   .subject(user.getEmail())
                   .claim("userId", user.getId())
                   .claim("role", user.getRole())
                   .claim("name", user.getName())
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis()*1000*60*60))
                   .signWith(getKey())
                   .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                   .verifyWith(getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }
}
