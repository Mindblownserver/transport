package org.acme.repositories;

import org.acme.entities.DrItin;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrItinRepository implements PanacheRepository<DrItin>{

}