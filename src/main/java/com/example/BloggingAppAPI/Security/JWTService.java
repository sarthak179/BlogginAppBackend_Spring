package com.example.BloggingAppAPI.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    //move the key to a separate .properties file
    private static final String JWT_KEY = "e3bhr435t347hg7354bgh56g7835hgb35gb358gth569";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJwt(Long userId) {
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                //.withExpiresAt()
                .sign(algorithm);
    }

    public Long retrieveUserId(String jwtString) {
        var decodedJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodedJWT.getSubject());
        return userId;
    }
}
