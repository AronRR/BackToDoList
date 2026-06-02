package org.acme.interfaces.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.dto.CreateListaDto;
import org.acme.application.dto.UpdateListaDto;
import org.acme.application.usecase.CreateListaUseCase;
import org.acme.application.usecase.DeleteListaUseCase;
import org.acme.application.usecase.GetListaByIdUseCase;
import org.acme.application.usecase.GetUserListasUseCase;
import org.acme.application.usecase.UpdateListaUseCase;
import org.acme.application.usecase.GetListaTodosUseCase;

import java.util.UUID;

@Path("/listas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListaResource {

    private final CreateListaUseCase createListaUseCase;
    private final GetUserListasUseCase getUserListasUseCase;
    private final GetListaByIdUseCase getListaByIdUseCase;
    private final UpdateListaUseCase updateListaUseCase;
    private final DeleteListaUseCase deleteListaUseCase;
    private final GetListaTodosUseCase getListaTodosUseCase;

    @Inject
    public ListaResource(CreateListaUseCase createListaUseCase,
                         GetUserListasUseCase getUserListasUseCase,
                         GetListaByIdUseCase getListaByIdUseCase,
                         UpdateListaUseCase updateListaUseCase,
                         DeleteListaUseCase deleteListaUseCase,
                         GetListaTodosUseCase getListaTodosUseCase) {
        this.createListaUseCase = createListaUseCase;
        this.getUserListasUseCase = getUserListasUseCase;
        this.getListaByIdUseCase = getListaByIdUseCase;
        this.updateListaUseCase = updateListaUseCase;
        this.deleteListaUseCase = deleteListaUseCase;
        this.getListaTodosUseCase = getListaTodosUseCase;
    }

    @POST
    public Response createLista(CreateListaDto dto) {
        return Response.status(Response.Status.CREATED)
                .entity(createListaUseCase.execute(dto))
                .build();
    }

    @GET
    public Response getUserListas() {
        return Response.ok(getUserListasUseCase.execute()).build();
    }

    @GET
    @Path("/{id}")
    public Response getListaById(@PathParam("id") UUID id) {
        return Response.ok(getListaByIdUseCase.execute(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateLista(@PathParam("id") UUID id, UpdateListaDto dto) {
        return Response.ok(updateListaUseCase.execute(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLista(@PathParam("id") UUID id) {
        deleteListaUseCase.execute(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/todos")
    public Response getListaTodos(@PathParam("id") UUID id) {
        return Response.ok(getListaTodosUseCase.execute(id)).build();
    }
}
