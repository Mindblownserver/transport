package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.DrStati;

@ApplicationScoped
public class DrStatiRepo implements PanacheRepository<DrStati> {
}
