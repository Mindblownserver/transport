package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.DrItin;

@ApplicationScoped
public class DrItinRepo implements PanacheRepository<DrItin> {
}
