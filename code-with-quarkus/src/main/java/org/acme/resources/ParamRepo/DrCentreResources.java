package org.acme.resources;

import org.acme.repositories.DrCentreRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/centres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DrCentreResources {

    @Inject
    DrCentreRepo centreRepo;

    @GET
    public Response getCentres(){
        return Response.ok(centreRepo.listAll()).build();
    }
}
