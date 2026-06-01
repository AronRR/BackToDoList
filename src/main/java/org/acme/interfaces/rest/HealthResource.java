package org.acme.interfaces.rest;

import com.google.firebase.FirebaseApp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.infrastructure.config.FirebaseConfig;

import java.util.stream.Collectors;

@Path("/health")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

    @GET
    public Response health() {
        boolean firebaseOk = !FirebaseApp.getApps().isEmpty();
        String apps = FirebaseApp.getApps().stream()
                .map(FirebaseApp::getName)
                .collect(Collectors.joining(","));
        String error = FirebaseConfig.lastError != null ? FirebaseConfig.lastError.replace("\"", "'") : "";
        String credPath = System.getenv("FIREBASE_CREDENTIALS");
        boolean fileExists = credPath != null && new java.io.File(credPath).exists();
        return Response.ok("{\"firebase_initialized\":" + firebaseOk
                + ",\"apps\":\"" + apps + "\""
                + ",\"cred_path\":\"" + (credPath != null ? credPath : "null") + "\""
                + ",\"file_exists\":" + fileExists
                + ",\"error\":\"" + error + "\"}").build();
    }
}
