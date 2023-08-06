package com.bonobono.backend.auth.oauth.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class KakaoTokenValidate implements CustomTokenValidator {

    @Value("${spring.oauth2.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.oauth2.kakao.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.provider.kakao.user-info-uri}")
    private String USER_INFO_URL;

    private final ObjectMapper mapper;

    @Override
    public Map<String, Object> validate(String idTokenString) throws GeneralSecurityException, IOException {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // header를 생성해서 access token을 넣어준다
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + idTokenString);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(header);

        // profile api로 생성한 헤더를 담아서 요청을 보낸다
        ResponseEntity<String> response = restTemplate.exchange(
                USER_INFO_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        String body = response.getBody();

        Map<String, Object> map = mapper.<HashMap>readValue(body, HashMap.class);

        LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) map.get("kakao_account");

        System.out.println(map.get("kakao_account").getClass().toString());

        System.out.println(map);

        return hashMap;

    }
}
