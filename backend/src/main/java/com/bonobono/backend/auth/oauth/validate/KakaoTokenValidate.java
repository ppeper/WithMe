package com.bonobono.backend.auth.oauth.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class KakaoTokenValidate implements CustomTokenValidator {

    @Value("${spring.oauth2.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.oauth2.kakao.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.oauth2.kakao.redirect-uri}")
    private String REDIRECT_URL;

    @Value("${spring.provider.kakao.token_uri}")
    private String TOKEN_URL;

    @Value("${spring.provider.kakao.user-info-uri}")
    private String USER_INFO_URL;

    private final ObjectMapper mapper;

    public Map<String, Object> getTokens(String ioTokenString) throws IOException {
        log.info("getTokens 들어왔나?");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client-id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URL);
        params.add("code", ioTokenString);

        log.info(params.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_URL, request, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), HashMap.class);
    }

    @Override
    public Map<String, Object> validate(String idTokenString) throws GeneralSecurityException, IOException {
        log.info("validate 들어왔나?");

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> accessTokenAndRefreshToken = getTokens(idTokenString);
        log.info(accessTokenAndRefreshToken.toString());
        String accessToken = (String) accessTokenAndRefreshToken.get("access_token");
        String refreshToken = (String) accessTokenAndRefreshToken.get("refresh_token");

        log.info(idTokenString);
        log.info(accessToken + " " + refreshToken);

        // header를 생성해서 access token을 넣어준다
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>("parameters", header);


        // profile api로 생성한 헤더를 담아서 요청을 보낸다
        ResponseEntity<String> response = restTemplate.exchange(
                USER_INFO_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        log.info(String.valueOf(response));

        String body = response.getBody();

        Map<String, Object> map = mapper.<HashMap>readValue(body, HashMap.class);

        LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) map.get("kakao_account");

        System.out.println(map.get("kakao_account").getClass().toString());

        System.out.println(map);

        return hashMap;

    }
}
