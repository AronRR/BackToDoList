package org.acme.infrastructure.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import java.io.FileInputStream;
import java.io.InputStream;

@Startup
@ApplicationScoped
public class FirebaseConfig {

    void onStart(@Observes StartupEvent ev) {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                InputStream serviceAccount;
                String filePath = System.getenv("FIREBASE_CREDENTIALS_PATH");
                if (filePath != null && !filePath.isBlank()) {
                    System.out.println("Firebase: loading from file " + filePath);
                    serviceAccount = new FileInputStream(filePath);
                } else {
                    String classpathName = "airtellecta-1d1ca-firebase-adminsdk-fbsvc-fd3f5feafb.json";
                    serviceAccount = Thread.currentThread().getContextClassLoader().getResourceAsStream(classpathName);
                    System.out.println("Firebase: loading from classpath, found=" + (serviceAccount != null));
                }
                try (InputStream is = serviceAccount) {
                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(is))
                            .build();
                    FirebaseApp.initializeApp(options);
                    System.out.println("Firebase: initialized successfully");
                }
            }
        } catch (Exception e) {
            System.err.println("Firebase init failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
