package org.acme.resources;



import org.acme.repositories.SHAPSRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/SHAPS")
@Produces(MediaType.APPLICATION_JSON)
public class SHAPSResources { 
    
@Inject
    SHAPSRepo SHAPSRepo;

    @GET
    public Response get(){
        return Response.ok(SHAPSRepo.listAll()).build();
    }
    
}
