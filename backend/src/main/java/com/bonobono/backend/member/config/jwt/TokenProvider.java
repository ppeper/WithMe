package com.bonobono.backend.member.config.jwt;

import com.bonobono.backend.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final String SECRET_KEY;
    private final String FEFRESH_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60; // 1시간
    private final Long RFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 30; // 30일
    private final String AUTHORITIES_KEY = "auth";

    @Autowired
    private MemberRepository memberRepository;

    public TokenProvider(
        @Value("secret-key") String secretKey,
        @Value("refresh-key") String refreshKey
    ) {
        this.SECRET_KEY = secretKey;
        this.FEFRESH_KEY = refreshKey;
    }

//    /**
//     * Access Token 생성
//     * @params authentication
//     * @return
//     */
//    public String createToken(Authentication authentication) {
//
//    }
    public static String createToken(String accountId, String key, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("accountId", accountId);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
            .signWith(SignatureAlgorithm.HS256, key)
            .compact();
    }
}
