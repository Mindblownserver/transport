package org.acme.repositories;

import org.acme.entities.Agent;
import org.acme.entities.DrCentre;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrCentreRepository implements PanacheRepository<DrCentre>{

    public DrCentre getDrCentreById(long dec) {
        return find ("DECCENT",dec).singleResult();
    }


}
