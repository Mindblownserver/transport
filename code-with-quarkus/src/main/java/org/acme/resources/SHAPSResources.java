package org.acme.resources;



import org.acme.repositories.SHAPSRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/shaps")
@Produces(MediaType.APPLICATION_JSON)
public class SHAPSResources { 
    
@Inject
    SHAPSRepo shapsRepo;

    @GET
    public Response get(){
        return Response.ok(shapsRepo.listAll()).build();
    }
    
}
