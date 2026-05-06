package com.colegio.msapigateway.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                InputStream serviceAccountStream = getCredentialsStream();

                FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase inicializado correctamente");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar Firebase: " + e.getMessage(), e);
        }
    }

    private InputStream getCredentialsStream() {
        String firebaseConfig = System.getProperty("FIREBASE_CONFIG");

        if (firebaseConfig == null || firebaseConfig.isEmpty()) {
            firebaseConfig = System.getenv("FIREBASE_CONFIG");
        }

        if (firebaseConfig != null && !firebaseConfig.isEmpty()) {
            return new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));
        }

        throw new RuntimeException("FIREBASE_CONFIG no encontrado");
    }
}