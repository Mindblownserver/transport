package org.acme.repositories;

import org.acme.entities.DrCentre;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrCentreRepo implements PanacheRepository<DrCentre>{

}
