package com.social.instagram.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.social.instagram.exception.FirebaseNotConnectedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/*
    FirebaseOptions.Builder() deprecated
    https://firebase.google.com/docs/reference/admin/java/reference/com/google/firebase/FirebaseOptions.Builder
*/

@Configuration
public class FirebaseConfig {

    @Value("${firebase.location}")
    private String fileLocation;

    @Value("${firebase.error.message}")
    private String errorMessage;

    @Bean
    public void firebaseConnection() {
        try(InputStream stream = new ClassPathResource(fileLocation).getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new FirebaseNotConnectedException(errorMessage);
        }

    }

}