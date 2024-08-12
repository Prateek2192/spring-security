package com.example.spring.security.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.spring.security.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {

    @Value("${security.jwt.token.secret-key}")
    private String JWT_SECRET_KEY;


    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);

            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(getAccessExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new JWTCreationException("Exception raised while generating a JWT token", ex);
        }

    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);

            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("Error while validating JWT token", ex);
        }
    }


    private Instant getAccessExpirationDate() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+05:30"));
    }

}
