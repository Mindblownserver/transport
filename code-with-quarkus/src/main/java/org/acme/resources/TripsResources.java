package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Trips;
import org.acme.repositories.DrTripsRepo;

import java.util.Date;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
@Path("/api/trips")
@Produces(MediaType.APPLICATION_JSON)
public class TripsResources {
    @Inject
    DrTripsRepo tripsRepo;
    @GET
    @Path("/trips")
    public Response getTrips(){
        List<Trips> listTrips=tripsRepo.listAll();
        if(listTrips.size()>0){
            return Response.ok(listTrips).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    @GET
    @Path("/trips/{date}")
    public Response getTripsByDate(@PathParam("date") Date date) {
        List<Trips> tripList = tripsRepo.findByDate(date);
        return Response.ok(tripList).build();
    }
}