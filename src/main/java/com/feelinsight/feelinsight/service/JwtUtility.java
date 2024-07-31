package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtility {
    private final String secret="yourSecretKey";

    private static final long EXPIRATION_TIME = 1000L * 60 * 60;

    public String generateToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (SignatureException | ExpiredJwtException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }catch (Exception e) {
            throw new InvalidTokenException("토큰 검증 중 오류가 발생했습니다.")
        }
    }

    public String bearerToken(String token){
        if(token != null){
            return token.replace("Bearer ","");
        }else{
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }

    }
}
