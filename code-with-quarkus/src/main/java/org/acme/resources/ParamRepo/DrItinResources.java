package org.acme.resources.ParamRepo;

import java.util.List;

import org.acme.entities.DrItin;
import org.acme.repositories.DrItinRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/itin")
@Produces(MediaType.APPLICATION_JSON)

public class DrItinResources {
    @Inject
    DrItinRepo itinRepo;

    @GET
    public Response getAll(){
        List<DrItin> intins = itinRepo.listAll();
        if(intins.size()> 0) {
            return Response.ok(intins).build();
        }
        return Response.status(Status.NOT_FOUND).build();
        
    }
}
