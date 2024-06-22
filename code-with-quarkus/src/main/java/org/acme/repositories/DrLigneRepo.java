package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.DrCentre;
import org.acme.entities.DrDeleg;
import org.acme.entities.DrLigne;
import org.acme.entities.DrTypeLigne;

import java.util.List;

@ApplicationScoped
public class DrLigneRepo  implements PanacheRepository<DrLigne> {
    public List<DrLigne> findByDeccent(DrCentre deccent) {
        return find("centre", deccent).list();
    }

    /*public List<DrLigne> findByDeleg(DrDeleg deleg) { return find("decDeleg", deleg).list();}*/
    public List<DrLigne> findByTypLi(DrTypeLigne typeLi) {return find("type", typeLi).list();}


}
