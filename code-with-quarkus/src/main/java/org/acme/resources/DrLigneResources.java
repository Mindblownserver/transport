package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.repositories.DrLigneRepo;

@Path("/api/ligne")
@Produces(MediaType.APPLICATION_JSON)
public class DrLigneResources {
    @Inject
    DrLigneRepo ligneRepo;
    @GET
    public Response getLigne(){
        return Response.ok(ligneRepo.listAll()).build();
    }
}
