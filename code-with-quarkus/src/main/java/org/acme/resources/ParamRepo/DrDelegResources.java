package org.acme.resources.ParamRepo;

import org.acme.repositories.DrDelegRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/deleg")
@Produces(MediaType.APPLICATION_JSON)

public class DrDelegResources{
    @Inject
    DrDelegRepo delegRepo;

    @GET
    public Response get(){
        return Response.ok(delegRepo.listAll()).build();
    }
}
