package org.acme.repositories;

import org.acme.entities.Drligne;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DrligneRepo  implements PanacheRepository<Drligne>{
    

}
