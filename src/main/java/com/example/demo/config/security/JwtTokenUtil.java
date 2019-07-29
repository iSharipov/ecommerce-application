package com.example.demo.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.config.props.JwtProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private final JwtProperties jwtProperties;

    public JwtTokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, DecodedJWT::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, DecodedJWT::getExpiresAt);
    }

    public <T> T getClaimFromToken(String token, Function<DecodedJWT, T> claimsResolver) {
        final DecodedJWT claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private DecodedJWT getAllClaimsFromToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtProperties.getSecret())).build();
        return verifier.verify(token);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return doGenerateToken(userDetails.getUsername());
    }

    private String doGenerateToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getTokenValidity() * 1000))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

