package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.repositories.DrItinRepo;

@Path("/api/itin")
@Produces(MediaType.APPLICATION_JSON)
public class DrItinResources {
    @Inject
    DrItinRepo itinRepo;

    @GET
    public Response getItin(){
        return Response.ok(itinRepo.listAll()).build();
    }
}
