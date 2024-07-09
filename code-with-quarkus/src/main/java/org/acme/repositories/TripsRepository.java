package org.acme.repositories;

import java.util.Date;
import java.util.List;

import org.acme.entities.Trips;
import org.jboss.logging.Logger;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TripsRepository  implements PanacheRepository<Trips> {
    @Inject
    Logger log;

    public List<Trips> findByDate(Date date) {
        return find ("tripsId.deDated",date).list();
    }

    public List<Trips> getTripsWithLastStop(int row){
        List<Trips> trips = findAll().page(0,row).list();
        return trips;
    }
    public List<Trips> getTripsWithLastStopByDate(Date deDated){
        log.debug(deDated);
        List<Trips> trips = find("tripsId.deDated",deDated).list();
        log.debug("finished this part");
        return trips;
    }
    /* public List<Trips> */
    // inject StopTimes Repo 
    // prepare needed List with finalStopTimes configured
    // return a list that'll be used in busResource

}