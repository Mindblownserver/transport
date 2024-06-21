package org.acme.repositories;

import org.acme.entities.StopTimes;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StopTimesRepo implements PanacheRepository<StopTimes>{

}
