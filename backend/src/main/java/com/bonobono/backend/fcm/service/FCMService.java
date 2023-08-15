package com.bonobono.backend.fcm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    private final ObjectMapper objectMapper;

    @Autowired
    public FCMService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getAccessToken() throws IOException {
        // firebase로 부터 access token을 가져온다.

        GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new ClassPathResource("firebase/bonobono-2a773-firebase-adminsdk-70319-9beeb00ab4.json").getInputStream())
            .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();
    }

}
