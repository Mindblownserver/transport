package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Compte;

@ApplicationScoped
public class CompteRepo implements PanacheRepository<Compte> {

    public Compte findByUsername(String username) {
        return find("username", username).singleResult();
    }

    public Compte findByMobile(Integer mobile) {
        return find("mobile", mobile).singleResult();
    }
}
