package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.DrLigne;

@ApplicationScoped
public class DrLigneRepo implements PanacheRepository<DrLigne> {
}
