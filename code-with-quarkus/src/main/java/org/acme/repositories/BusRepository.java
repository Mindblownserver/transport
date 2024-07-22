package org.acme.repositories;

import org.acme.entities.Bus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusRepository implements PanacheRepository<Bus>{

}
