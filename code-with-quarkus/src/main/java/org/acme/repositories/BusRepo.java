package org.acme.repositories;

import org.acme.entities.Bus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusRepo implements PanacheRepository<Bus>{

}
