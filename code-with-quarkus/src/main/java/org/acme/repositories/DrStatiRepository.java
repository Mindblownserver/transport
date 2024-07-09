package org.acme.repositories;

import org.acme.entities.DrStati;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrStatiRepository implements PanacheRepository<DrStati>{

}
