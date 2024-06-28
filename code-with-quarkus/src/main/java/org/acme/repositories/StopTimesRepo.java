package org.acme.repositories;

import java.util.Date;

import org.acme.Embeddable.TripsId;
import org.acme.entities.StopTimes;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StopTimesRepo implements PanacheRepository<StopTimes>{
    public Date getLastStopTimes(TripsId tripsId){
        StopTimes lastStop =  find("id.tripsDatedId",Sort.by("id.stopSeq").descending() ,tripsId).page(0, 1).firstResult();
        return lastStop.getArrival_time();
    }
}
