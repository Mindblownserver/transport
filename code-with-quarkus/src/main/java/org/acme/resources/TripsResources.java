package org.acme.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.acme.entities.SQL.TripsSql;
import org.acme.repositories.TripsSqlRepository;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/trips")
public class TripsResources {
    /**
     * Trips Statistics record that stores statistics in a given date
     */
    public static class Statistics {
        private int nbrVoyages;
        private int nbrAller;
        private int nbrRetour;
        private int nbrLignesAffectes;
        private int nbrChauffRecAffectes[];
        private int nbrBusAffectes;
        private List<StatisticsItem> lignesParCentre;
        private List<StatisticsItem> busParCentre;
        private List<StatisticsItem> agentParCentre;

        public Statistics(int nbrVoyages, int nbrAller, int nbrRetour, 
                        int nbrLignesAffectes, 
                        int nbrBusAffectes) {
            this.nbrVoyages = nbrVoyages;
            this.nbrAller = nbrAller;
            this.nbrRetour = nbrRetour;
            this.nbrLignesAffectes = nbrLignesAffectes;
            this.nbrBusAffectes = nbrBusAffectes;
        }

        
        public int getNbrVoyages() {
            return nbrVoyages;
        }


        public int getNbrAller() {
            return nbrAller;
        }


        public int getNbrRetour() {
            return nbrRetour;
        }


        public int getNbrLignesAffectes() {
            return nbrLignesAffectes;
        }


        public int getNbrBusAffectes() {
            return nbrBusAffectes;
        }


        public List<StatisticsItem> getLignesParCentre() {
            return lignesParCentre;
        }


        public List<StatisticsItem> getBusParCentre() {
            return busParCentre;
        }


        public List<StatisticsItem> getAgentParCentre() {
            return agentParCentre;
        }


        public void setLignesParCentre(List<StatisticsItem> lignesParCentre) {
            this.lignesParCentre = lignesParCentre;
        }



        public void setBusParCentre(List<StatisticsItem> busParCentre) {
            this.busParCentre = busParCentre;
        }



        public void setAgentParCentre(List<StatisticsItem> agentParCentre) {
            this.agentParCentre = agentParCentre;
        }



        public void setNbrVoyages(int nbrVoyages) {
            this.nbrVoyages = nbrVoyages;
        }

        public void setNbrAller(int nbrAller) {
            this.nbrAller = nbrAller;
        }

        public void setNbrRetour(int nbrRetour) {
            this.nbrRetour = nbrRetour;
        }

        public void setNbrLignesAffectes(int nbrLignesAffectes) {
            this.nbrLignesAffectes = nbrLignesAffectes;
        }

        public void setNbrBusAffectes(int nbrBusAffectes) {
            this.nbrBusAffectes = nbrBusAffectes;
        }

        public static class StatisticsItem{
            public int label;
            public int value;
            public StatisticsItem(int l, int v){
                label=l;value=v;
            }
        }

        public int[] getNbrChauffRecAffectes() {
            return nbrChauffRecAffectes;
        }


        public void setNbrChauffRecAffectes(int[] nbrChauffRecAffectes) {
            this.nbrChauffRecAffectes = nbrChauffRecAffectes;
        }
    }
    
    @Inject
    TripsSqlRepository tripsSqlRepo;

    @Inject
    Logger log;
    
    @GET
    @Path("/date/{date}")
    public Response getTripsByDate(@PathParam("date") String date) throws ParseException {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate = formatter.parse(date);
        try{
            List<TripsSql> trips = tripsSqlRepo.getTripsByDate(parsedDate);
            return Response.ok(trips).build();
        }catch(Exception e){
            log.error(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
   
    @GET
    @Path("/statistics/{date}")
    public Response getTripsStatisticsByDate(@PathParam("date") String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate = formatter.parse(date);
        try{
            Statistics tripStatistics = tripsSqlRepo.getTripsStatisticsByDate(parsedDate);
            return Response.ok(tripStatistics).build();
        }catch(Exception e){
            return Response.status(400).entity(e.toString()).build();
        }
    }
}

