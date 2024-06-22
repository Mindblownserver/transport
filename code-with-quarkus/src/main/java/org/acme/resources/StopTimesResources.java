package org.acme.resources;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.ws.rs.PathParam;
import org.acme.entities.StopTimes;
import org.acme.entities.Trips;
import org.acme.repositories.DrTripsRepo;
import org.acme.repositories.StopTimesRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class StopTimesResources {

   @Inject
    StopTimesRepo stopTimesRepo;

    @GET
    @Path("/stoptimes")
    public Response getStoptimes(){
        List<StopTimes> listStops=stopTimesRepo.listAll();
        if(!listStops.isEmpty()){
            return Response.ok(listStops).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
     
    }

    @GET
    @Path("/stoptimes/{date}")
    public Response getStopTimeByDate(@PathParam("date") String date) throws ParseException {
        try{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        Date parsedDate = formatter.parse(date);
        List<StopTimes> listStoptimes = stopTimesRepo.findByDate(parsedDate);
        if(!listStoptimes.isEmpty()) return Response.ok(listStoptimes).build();
        } catch (ParseException ex){
            System.out.println("Erreur: Date non parsable");
        }return Response.status(Response.Status.NOT_FOUND).build();
    }
}
