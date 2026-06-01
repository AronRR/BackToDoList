package org.acme.interfaces.rest;

import com.google.firebase.FirebaseApp;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.RegisterUserDto;
import org.acme.application.usecase.RegisterUserUseCase;
import org.acme.infrastructure.security.AuthContext;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    RegisterUserUseCase registerUserUseCase;

    @Inject
    AuthContext authContext;

    public UserResource(RegisterUserUseCase registerUserUseCase, AuthContext authContext){
        this.registerUserUseCase = registerUserUseCase;
        this.authContext = authContext;
    }

    @POST
    public Response registerUser(@Valid RegisterUserDto registerUserDto) {
        try {
            return Response.ok(registerUserUseCase.execute(registerUserDto)).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Path("/test")
    @GET
    public Response testEndpoint(){
        System.out.println("En el endpoint: "+authContext.getUser().getId());
        return Response.ok("Hello").build();
    }
}

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
class HealthResource {
    @GET
    public Response health() {
        boolean firebaseOk = !FirebaseApp.getApps().isEmpty();
        String apps = FirebaseApp.getApps().stream()
                .map(a -> a.getName())
                .collect(java.util.stream.Collectors.joining(","));
        return Response.ok("{\"firebase_initialized\":" + firebaseOk + ",\"apps\":\"" + apps + "\"}").build();
    }
}
