package org.acme.repositories;

import org.acme.entities.DrDeleg;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrDelegRepository implements PanacheRepository<DrDeleg>{

}
