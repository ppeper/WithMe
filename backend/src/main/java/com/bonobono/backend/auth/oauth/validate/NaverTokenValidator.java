package com.bonobono.backend.auth.oauth.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

@Setter
@RequiredArgsConstructor
@Component
@Slf4j
public class NaverTokenValidator implements CustomTokenValidator {

    private final ObjectMapper mapper;

    @Value("${spring.oauth2.naver.client-id}")
    private String CLIENT_ID;

    @Value("${spring.oauth2.naver.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.provider.naver.token_uri}")
    private String TOKEN_URL;

    @Value("${spring.provider.naver.user-info-uri}")
    private String USER_INFO_URL;

    @Override
    public Map<String, Object> validate(String idTokenString) throws GeneralSecurityException, IOException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", "Bearer " + idTokenString);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<HashMap> response = restTemplate.exchange(
            "https://openapi.naver.com/v1/nid/me",
            HttpMethod.GET,
            entity,
            HashMap.class
        );

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> responseMap = (Map<String, Object>)response.getBody().get("response");

        resultMap.put("email", responseMap.get("email"));

        return resultMap;

//        RestTemplate restTemplate_1 = new RestTemplate();
//        HttpHeaders headers_1 = new HttpHeaders();
//        headers_1.add("Content-Type", "application/x-www-form-urlencoded");
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", CLIENT_ID);
//        params.add("client_secret", CLIENT_SECRET);
//        params.add("code", idTokenString);
//        params.add("state", "state");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params);
//
//        ResponseEntity<String> response = restTemplate_1.exchange(TOKEN_URL,
//                HttpMethod.POST, request, String.class);
//
//        RestTemplate restTemplate_2 = new RestTemplate();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> accessTokenAndRefreshToken = objectMapper.readValue(response.getBody(), HashMap.class);
//
//        String accessToken = (String) accessTokenAndRefreshToken.get("access_token");
//        String refreshToken = (String) accessTokenAndRefreshToken.get("refresh_token");
//
//        log.info(idTokenString);
//        log.info(accessToken + " " + refreshToken);
//
//        HttpHeaders headers_2 = new HttpHeaders();
//        headers_2.add("Authorization", "Bearer " + accessToken);
//        headers_2.add("X-Naver-Client-Id", "CLIENT_ID");
//        headers_2.add("X-Naver-Client-Secret", "USER_INFO_URL");
//
//        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers_2);
//
//        ResponseEntity<LinkedHashMap> response_2 = restTemplate_2.exchange(
//                USER_INFO_URL,
//                HttpMethod.GET,
//                entity,
//                LinkedHashMap.class
//        );
//
//        Map<String, HashMap> map = response_2.getBody();
//        String naver_id = (String) map.get("response").get("id");
//
//        System.out.println(map);
//
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("email", naver_id);
//
//        return resultMap;

    }
}
