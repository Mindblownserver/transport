package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Embeddable.TripsId;
import org.acme.entities.StopTimes;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class StopTimesRepo implements PanacheRepository<StopTimes>{
    public List<StopTimes> findByDate(Date date) {
        return find ("stopTimeId.deDated",date).list();
    }
    public Date getLastStopTimes(TripsId tripsId){
        StopTimes lastStop =  find("id.tripsDatedId",Sort.by("id.stopSequence").descending() ,tripsId).firstResult();
        return lastStop.getArrival_time();
    }

}