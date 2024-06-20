package org.acme.resources;


import org.acme.repositories.DrtpliRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/tpli")
@Produces(MediaType.APPLICATION_JSON)
public class DrtpliResources {
    @Inject
    DrtpliRepo tpliRepo;

    @GET
    public Response get(){
        return Response.ok(tpliRepo.listAll()).build();
    }
    
}
