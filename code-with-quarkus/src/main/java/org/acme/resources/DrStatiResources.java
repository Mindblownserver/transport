package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.repositories.DrStatiRepo;

@Path("/api/stati")
@Produces(MediaType.APPLICATION_JSON)
public class DrStatiResources {
    @Inject
    DrStatiRepo statiRepo;

    @GET
    public Response getStati(){
        return Response.ok(statiRepo.listAll()).build();
    }
}
