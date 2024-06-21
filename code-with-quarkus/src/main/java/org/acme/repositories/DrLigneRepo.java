package org.acme.repositories;

import org.acme.entities.DrLigne;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DrLigneRepo  implements PanacheRepository<DrLigne>{
    

}
