package org.acme.infrastructure.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Startup
@ApplicationScoped
public class FirebaseConfig {

    @ConfigProperty(name="firebase.credentials")
    private String path;

    void onStart(@Observes StartupEvent ev) {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                // Try classpath first (works in both dev and production jar)
                InputStream serviceAccount = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(path);
                if (serviceAccount == null) {
                    // Fallback: try as file path (absolute or relative to CWD)
                    java.io.File f = new java.io.File(path);
                    if (!f.exists()) {
                        f = new java.io.File("src/main/resources/" + path);
                    }
                    System.err.println("Firebase credentials file: " + f.getAbsolutePath() + " exists=" + f.exists());
                    serviceAccount = new FileInputStream(f);
                }
                try (InputStream is = serviceAccount) {
                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(is))
                            .build();
                    FirebaseApp.initializeApp(options);
                }
            }
        } catch (Exception e) {
            System.err.println("Firebase init failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
