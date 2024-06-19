package org.acme.resources;

import org.acme.repositories.DrItinRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/itin")
@Produces(MediaType.APPLICATION_JSON)
public class DrItinResources {
    @Inject
    DrItinRepo itinRepo;

    @GET
    public Response getAll(){
        return Response.ok(itinRepo.listAll()).build();
    }
}
