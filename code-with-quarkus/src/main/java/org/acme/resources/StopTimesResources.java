package org.acme.resources;


import java.util.List;

import org.acme.entities.StopTimes;
import org.acme.repositories.StopTimesRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/Stop_times")
@Produces(MediaType.APPLICATION_JSON)
public class StopTimesResources {

   @Inject
    StopTimesRepository stopTimesRepo;

    @GET
    public Response get(){
        List<StopTimes> listStops=stopTimesRepo.listAll();
        if(listStops.size()>0){
            return Response.ok(listStops).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    @Path("/{row}")
    @GET
    public Response getRows(@PathParam("row") int row){
        List<StopTimes> listStops=stopTimesRepo.findAll().page(1, row).list();
        if(listStops.size()>0){
            return Response.ok(listStops).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}
