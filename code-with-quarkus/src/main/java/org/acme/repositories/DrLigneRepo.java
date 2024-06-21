package org.acme.repositories;

import java.util.List;

import org.acme.entities.DrLigne;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DrLigneRepo  implements PanacheRepository<DrLigne>{
    public List<DrLigne> findByTypeLigne(Long i){
        return find("type.decTyLi", i).list(); // where type.decTyLi = {i}
    }

    public List<DrLigne> findByDeleg(Long i){
        return find("deleg.decDeleg",i).list();
    }

}
