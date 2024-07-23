package org.acme.repositories;

import java.util.Date;
import java.util.List;

import org.acme.entities.Trips;
import org.jboss.logging.Logger;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TripsRepository  implements PanacheRepository<Trips> {
    @Inject
    Logger log;

    public List<Trips> findByDate(Date date) {
        return find ("tripsId.deDated",date).list();
    }

    public List<Trips> getTripsWithLastStop(int row){
        List<Trips> trips = findAll().page(0,row).list();
        return trips;
    }
    public List<Trips> getTripsWithLastStopByDate(Date deDated){
        log.debug(deDated);
        List<Trips> trips = find("tripsId.deDated",deDated).list();
        log.debug("finished this part");
        return trips;
    }

    public int getVoyagesByDate(Date date) {
        return find("tripsId.deDated= ?1 and tripNid is not null", date).list().size()/2;
    }
    public int getAllerByDate(Date date){
        return find("tripsId.deDated= ?1 and directionId=?2 and tripNid is null",date,0).list().size();
    }
    public int getRetourByDate(Date date){
        return find("tripsId.deDated= ?1 and directionId=?2 and tripNid is null",date,1).list().size();
    }
    public int getNbrLignesByDate(Date date){
        return find("select distinct t.ligne from Trips t where t.tripsId.deDated= ?1",date).list().size();
    }
    public int getNbrVehicByDate(Date date){
        return find("select distinct t.busPr from Trips t where t.tripsId.deDated= ?1",date).list().size();
    }
    public int getNbrAgentsByDate(Date date){
        return find("select distinct t.chauffPr, t.RecPr from Trips t where t.tripsId.deDated= ?1",date).list().size();
    }
}