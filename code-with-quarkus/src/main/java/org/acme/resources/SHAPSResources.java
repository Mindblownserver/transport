package org.acme.resources;



import org.acme.repositories.SHAPSRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

<<<<<<< HEAD
@Path("/api/SHAPS")
=======
@Path("/api/shaps")
>>>>>>> 24fd74b417ae2e385ae79538d03241820473c5c5
@Produces(MediaType.APPLICATION_JSON)
public class SHAPSResources { 
    
@Inject
<<<<<<< HEAD
    SHAPSRepo SHAPSRepo;

    @GET
    public Response get(){
        return Response.ok(SHAPSRepo.listAll()).build();
=======
    SHAPSRepo shapsRepo;

    @GET
    public Response get(){
        return Response.ok(shapsRepo.listAll()).build();
>>>>>>> 24fd74b417ae2e385ae79538d03241820473c5c5
    }
    
}
