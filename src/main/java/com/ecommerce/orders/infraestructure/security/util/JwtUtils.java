package com.ecommerce.orders.infraestructure.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private String privateKey= "5c169ddbe6c9f8f5e7a4f04fdec406333e9882cd289f6fd06c6b30c1a95e5697";
    private String userGenerator = "AUTH0JWT-BACKEND";

    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        String username = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt((new Date(System.currentTimeMillis()+86400000)))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            return verifier.verify(token);
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token invalido");
        }
    }

    public String getUsernameFromToken(DecodedJWT token) {
        return token.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT token, String claimName) {
        return token.getClaim(claimName);
    }
}
