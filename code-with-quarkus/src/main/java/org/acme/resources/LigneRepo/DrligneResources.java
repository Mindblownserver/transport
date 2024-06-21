package org.acme.resources.LigneRepo;
import org.acme.repositories.DrLigneRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/ligne")
@Produces(MediaType.APPLICATION_JSON)

public class DrligneResources {
    @Inject
    DrLigneRepo ligneRepo;

    @GET
    public Response get(){
        return Response.ok(ligneRepo.listAll()).build();
    }
    
}
