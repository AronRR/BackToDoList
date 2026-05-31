package org.acme.interfaces.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.usecase.SearchUseCase;

@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final SearchUseCase searchUseCase;

    @Inject
    public SearchResource(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
    }

    @GET
    public Response search(@QueryParam("q") String query) {
        return Response.ok(searchUseCase.execute(query)).build();
    }
}
