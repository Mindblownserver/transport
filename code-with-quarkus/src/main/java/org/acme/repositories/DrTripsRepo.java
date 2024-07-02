package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entities.Trips;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class DrTripsRepo implements PanacheRepository<Trips> {
    @Inject
    StopTimesRepo stopTimesRepo;


    public List<Trips> findByDate(Date date) {
        return find ("tripsId.deDated",date).list();
    }

}
