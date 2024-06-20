package org.acme.repositories;


import org.acme.entities.SHAPS;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SHAPSRepo implements PanacheRepository<SHAPS>{


}
