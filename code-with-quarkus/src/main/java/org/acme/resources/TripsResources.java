package org.acme.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.acme.entities.StopTimes;
import org.acme.entities.Trips;
import org.acme.repositories.TripsRepo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/trips")
public class TripsResources {
    @Inject
    TripsRepo tripsRepo;
    @GET
    public Response getTrips(){
        List<Trips> listTrips=tripsRepo.listAll();
        if(listTrips.size()>0){
            return Response.ok(listTrips).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    @GET
    @Path("/date/{date}")
    public Response getTripsByDate(@PathParam("date") String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        Date parsedDate = formatter.parse(date);
        List<Trips> listTrips = tripsRepo.findByDate(parsedDate);
        if(!listTrips.isEmpty()){
            return Response.ok(listTrips).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    // dev only
    @GET
    @Path("/{row}")
    public Response getTripsByRow(@PathParam("row") int row) throws ParseException {
        List<Trips> listStops=tripsRepo.findAll().page(1, row).list();
        if(listStops.size()>0){
            return Response.ok(listStops).build();
        }else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
