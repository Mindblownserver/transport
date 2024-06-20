package org.acme.repositories;

import org.acme.entities.DrTypeLigne;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrTypeLigneRepo implements PanacheRepository<DrTypeLigne> {


}
