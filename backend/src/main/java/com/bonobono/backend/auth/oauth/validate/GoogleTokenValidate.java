package com.bonobono.backend.auth.oauth.validate;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleTokenValidate implements CustomTokenValidator {

    @Value("${spring.oauth2.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Override
    public Map<String, Object> validate(String idTokenString) throws GeneralSecurityException, IOException {

        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();

        // 토큰 검증을 위해 객체 생성
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // 백엔드에 접근하는 애플리케이션 클라이언트 ID 저장
                // 여러 클라이언트가 접근하는 경우 배열로 추가 가능
                // .setAudience(Arryas.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID3))
                .build();

        // HTTP Post를 통해 idTokenString 받음

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            Payload payload = idToken.getPayload();

            // 사용자 식별자 출력
            String memberId = payload.getSubject();
            System.out.println("Member ID: " + memberId);

            // Payload 로부터 프로필 정보 가져오기
            String username = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String profileImg = (String) payload.get("picture");
            String local = (String) payload.get("local");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            map.put("email", username);
            map.put("name", name);
            map.put("picture", profileImg);
            return map;
        } else {
            System.out.println("Invalid Token");
            return null;
        }
    }
}
