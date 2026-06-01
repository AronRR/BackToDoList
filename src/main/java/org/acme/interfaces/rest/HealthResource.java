package org.acme.interfaces.rest;

import com.google.firebase.FirebaseApp;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
        return Response.ok("{\"firebase_initialized\":" + firebaseOk + ",\"apps\":\"" + apps + "\"}").build();
    }
}
