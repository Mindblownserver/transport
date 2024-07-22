package org.acme.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.acme.entities.Trips;
import org.acme.entities.SQL.TripsSql;
import org.acme.repositories.TripsRepository;
import org.acme.repositories.SQL.TripsSqlRepository;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/trips")
public class TripsResources {
    @Inject
    TripsRepository tripsRepo;

    @Inject
    TripsSqlRepository tripsSqlRepo;

    @Inject
    Logger log;

    @GET
    public Response getTrips(){
        List<Trips> listTrips=tripsRepo.listAll();
        if(listTrips.size()>0){
            return Response.ok(listTrips.size()).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    @GET
    @Path("/date/{date}")
    public Response getTripsByDate(@PathParam("date") String date) throws ParseException {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate = formatter.parse(date);
        try{
            List<TripsSql> trips = tripsSqlRepo.getTripsByDate(parsedDate);
            log.debug("Trips=" + trips);
            return Response.ok(trips).build();
        }catch(Exception e){
            log.error(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
/* 
    @GET
    @Path("/test")
    public Response getTripsTest(){
        try{
            List<TripsSql> trips = tripsSqlRepo.getTripsByDate();
            log.debug("Trips=" + trips);
            return Response.ok(trips).build();
        }catch(Exception e){
            log.error(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
    } */
   
    // dev only
    @GET
    @Path("/{row}")
    public Response getTripsByRow(@PathParam("row") int row) throws ParseException {
        //List<Trips> listStops=tripsRepo.findAll().page(1, row).list();
        List<Trips> listStops= tripsRepo.getTripsWithLastStop(row);
        if(listStops.size()>0){
            return Response.ok(listStops).build();
        }else {
            
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
