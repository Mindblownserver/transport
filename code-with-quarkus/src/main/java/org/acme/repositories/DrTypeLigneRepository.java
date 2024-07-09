package org.acme.repositories;

import org.acme.entities.DrTypeLigne;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DrTypeLigneRepository implements PanacheRepository<DrTypeLigne> {
    public boolean existe(Long i){
        return find("decTyLi", i).firstResult() !=null;
    }
}
