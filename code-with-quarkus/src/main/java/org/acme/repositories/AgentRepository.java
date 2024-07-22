package org.acme.repositories;

import java.util.List;

import org.acme.entities.Agent;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AgentRepository implements PanacheRepository<Agent> {

    public Agent findByDec(long dec) {
        return find ("decagen",dec).singleResult();
    }

    public List<Agent> findByNom(String denagea) {
        return find ("denagea",denagea).list();
    }

}