package org.acme.infrastructure.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            request.abortWith(Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type,Authorization,Accept")
                    .build());
        }
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.getHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization,Accept");
    }
}
