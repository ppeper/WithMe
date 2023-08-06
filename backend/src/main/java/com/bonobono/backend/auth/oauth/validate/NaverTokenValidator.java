package com.bonobono.backend.auth.oauth.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
public class NaverTokenValidator implements CustomTokenValidator {

    private final ObjectMapper mapper;

    @Value("${spring.oauth2.naver.client-id}")
    private String CLIENT_ID;

    @Value("${spring.oauth2.naver.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.provider.naver.user-info-uri}")
    private String USER_INFO_URL;

    @Override
    public Map<String, Object> validate(String idTokenString) throws GeneralSecurityException, IOException {

//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/x-www-form-urlencoded");
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", CLIENT_ID);
//        params.add("client_secret", CLIENT_SECRET);
//        params.add("code", idTokenString);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange("https://nid.naver.com/oauth2.0/token",
//                HttpMethod.POST, request, String.class);
//
//        Map<String, Object> map = new HashMap<String, Object>();

        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + idTokenString);
        headers.add("X-Naver-Client-Id", "CLIENT_ID");
        headers.add("X-Naver-Client-Secret", "USER_INFO_URL");

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(
                USER_INFO_URL,
                HttpMethod.GET,
                entity,
                LinkedHashMap.class
        );

        Map<String, HashMap> map = response.getBody();
        String naver_id = (String) map.get("response").get("id");

        System.out.println(map);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("email", naver_id);

        return resultMap;

    }
}
