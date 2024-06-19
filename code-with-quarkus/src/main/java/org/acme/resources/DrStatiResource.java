package org.acme.resources;

import org.acme.repositories.DrStatiRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/stations")
@Produces(MediaType.APPLICATION_JSON)
public class DrStatiResource {
    @Inject
    DrStatiRepo statRepo;
    
    @GET
    public Response get(){
        return Response.ok(statRepo.listAll()).build();
    }   
}
