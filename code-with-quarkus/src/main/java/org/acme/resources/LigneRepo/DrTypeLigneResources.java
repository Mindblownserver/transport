package org.acme.resources.LigneRepo;


import org.acme.repositories.DrTypeLigneRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/ligne/type")
@Produces(MediaType.APPLICATION_JSON)
public class DrTypeLigneResources {
    @Inject
    DrTypeLigneRepo tpliRepo;

    @GET
    public Response get(){
        return Response.ok(tpliRepo.listAll()).build();
    }
    
}
