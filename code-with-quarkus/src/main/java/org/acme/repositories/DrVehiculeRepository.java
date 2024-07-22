package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.DrVehicule;


@ApplicationScoped
public class DrVehiculeRepository implements PanacheRepository<DrVehicule> {

    public DrVehicule findByDec(long dec) {
        return find ("decodvh",dec).singleResult();
    }

    public DrVehicule findByMatricule(String dematri) {
        return find ("dematri",dematri).singleResult();
    }

}