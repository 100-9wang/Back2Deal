package com.myspring.back2deal.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long expiration;

    // 생성자 주입 (application.properties 값 불러오기)
    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
    }

    // AccessToken 발급 메서드
    public String generateAccessToken(String memId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(memId)          // 사용자 식별자 값
                .setIssuedAt(now)           // 발급 시간
                .setExpiration(expiryDate)  // 만료 시간
                .signWith(key, SignatureAlgorithm.HS512)  // 서명
                .compact();
    }

    // 토큰 검증 (유효한 토큰인지 확인)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 memId 추출
    public String getMemIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
