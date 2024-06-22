package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.StopTimes;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class StopTimesRepo implements PanacheRepository<StopTimes>{
    public List<StopTimes> findByDate(Date date) {
        return find ("stopTimeId.deDated",date).list();
    }

}