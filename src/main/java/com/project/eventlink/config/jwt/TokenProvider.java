package com.project.eventlink.config.jwt;

import com.project.eventlink.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties properties;

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), member);
    }

    //JWT 토큰 생성
    private String makeToken(Date expire, Member member) {
        Date now = new Date();
        byte[] keyBytes = Base64.getDecoder().decode(properties.getSecretKey());

        Claims claims = Jwts.claims();
        claims.put("id", member.getMemberId());
        claims.put("role", member.getRole());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expire)
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
                .compact();
    }

    //JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(properties.getSecretKey());
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(keyBytes)).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Validate Jwt Token Error");
            e.printStackTrace();
            return false;
        }
    }

    //토큰 기반 Member Id 가져오는 메서드
    public String getMemberId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", String.class);
    }

    private Claims getClaims(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(properties.getSecretKey());
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
