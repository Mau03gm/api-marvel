package com.MarvelDemo.demo.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static String secretKey="DdemoMarvel";
    private static Algorithm algorithm;

    static {
        try {
            algorithm = Algorithm.HMAC256(secretKey);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e+"ALGORITHM ERROR");
        }
    }

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("Marvel API")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30)))
                .sign(algorithm);
    }

    public boolean verifyToken(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return JWT.require(algorithm).build().verify(token).getSubject();
    }



}
