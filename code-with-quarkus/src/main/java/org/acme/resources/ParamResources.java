package org.acme.resources;

import java.util.List;

import org.acme.entities.DrCentre;
import org.acme.entities.StopTimes;
import org.acme.repositories.DrCentreRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParamResources {
    
    @Inject
    DrCentreRepo centreRepo;

    /*
     * 
     */
    @GET
    @Path("/centre")
    public Response getCentres(){

        List<DrCentre> listCentres=centreRepo.listAll();
        if(listCentres.size()>0){
            return Response.ok(listCentres).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
       
    }

    
}
