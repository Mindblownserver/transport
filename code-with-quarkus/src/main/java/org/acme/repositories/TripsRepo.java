package org.acme.repositories;

import java.util.Date;
import java.util.List;

import org.acme.entities.Trips;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TripsRepo  implements PanacheRepository<Trips> {
    @Inject
    StopTimesRepo stopTimesRepo;

    public List<Trips> findByDate(Date date) {
        return find ("tripsId.deDated",date).list();
    }

    public List<Trips> getTripsWithLastStop(int row){
        List<Trips> trips = findAll().page(0,row).list();
        for(Trips trip: trips)
            trip.setFinalStopTime(stopTimesRepo.getLastStopTimes(trip.getTripsId()));
        return trips;
    }
    /* public List<Trips> */
    // inject StopTimes Repo 
    // prepare needed List with finalStopTimes configured
    // return a list that'll be used in busResource

}