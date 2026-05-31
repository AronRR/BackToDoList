package org.acme.interfaces.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.CreateTodoDto;
import org.acme.application.dto.UpdateTodoDto;
import org.acme.application.usecase.CreateTodoUseCase;
import org.acme.application.usecase.DeleteTodoUseCase;
import org.acme.application.usecase.GetUserTodosUseCase;
import org.acme.application.usecase.UpdateTodoUseCase;

import java.util.UUID;

@Path("/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final CreateTodoUseCase createTodoUseCase;
    private final GetUserTodosUseCase getUserTodosUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;

    @Inject
    public TodoResource(CreateTodoUseCase createTodoUseCase,
                        GetUserTodosUseCase getUserTodosUseCase,
                        UpdateTodoUseCase updateTodoUseCase,
                        DeleteTodoUseCase deleteTodoUseCase) {
        this.createTodoUseCase = createTodoUseCase;
        this.getUserTodosUseCase = getUserTodosUseCase;
        this.updateTodoUseCase = updateTodoUseCase;
        this.deleteTodoUseCase = deleteTodoUseCase;
    }

    @POST
    public Response createTodo(CreateTodoDto dto) {
        return Response.status(Response.Status.CREATED)
                .entity(createTodoUseCase.execute(dto))
                .build();
    }

    @GET
    public Response getUserTodos() {
        return Response.ok(getUserTodosUseCase.execute()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTodo(@PathParam("id") UUID id, UpdateTodoDto dto) {
        return Response.ok(updateTodoUseCase.execute(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") UUID id) {
        deleteTodoUseCase.execute(id);
        return Response.noContent().build();
    }
}
