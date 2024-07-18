package org.acme.entities.SQL;

import java.util.Date;
import java.util.List;

import org.acme.Embeddable.TripsId;
import org.acme.entities.Agent;
import org.acme.entities.DrLigne;
import org.acme.entities.DrVehicule;
import org.acme.entities.StopTimes;


public class TripsSql {
    private TripsId tripsId;
    
    private Integer serviceId;
    
    private Integer directionId;
    
    private Date timeDepart;
    
    private Integer haveret;
    
    private String timeNret;
    
    private Integer tripNid;
    
    private Integer grp;
    
    private DrVehicule busPr;

    private DrVehicule busRe;
    
    private Agent chauffPr;
    
    private Integer chauffRe;
    
    private Agent RecPr;
    
    private Integer RecRe;
    
    private Integer etat;
    
    private Date timeDepartR;
    
    private Date timeArriveR;
    
    private Integer vMax;
    
    private Integer avanceRetard;
    
    private Integer changement;
    
    private String metaData;
    
    private Integer deValid;
    
    private Integer alert;
    
    private DrLigne ligne;

    private List<StopTimes> stopTimesList; 

    public Date getFinalStopTime() {
        return stopTimesList.get(stopTimesList.size()-1).getArrival_time();
    }

    // link decState with the needed station
    public String getTripName(){
        return ligne.getNomLigne();
    }
    public DrLigne getLigne(){
        return ligne;
    }


    // for info to be exposed into your JSON response, you need to prepare their getters & setters(optional)

    public TripsId getTripsId() {
        return tripsId;
    }
    
    public List<StopTimes> getStopTimesList() {
        return stopTimesList;
    }

    public void setStopTimesList(List<StopTimes> stopTimesList) {
        this.stopTimesList = stopTimesList;
    }

    public void setTripsId(TripsId tripsId) {
        this.tripsId = tripsId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public Date getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(Date timeDepart) {
        this.timeDepart = timeDepart;
    }

    public Integer getHaveret() {
        return haveret;
    }

    public void setHaveret(Integer haveret) {
        this.haveret = haveret;
    }

    public String getTimeNret() {
        return timeNret;
    }

    public void setTimeNret(String timeNret) {
        this.timeNret = timeNret;
    }

    public Integer getTripNid() {
        return tripNid;
    }

    public void setTripNid(Integer tripNid) {
        this.tripNid = tripNid;
    }

    public Integer getGrp() {
        return grp;
    }

    public void setGrp(Integer grp) {
        this.grp = grp;
    }

    public Integer getChauffRe() {
        return chauffRe;
    }

    public void setChauffRe(Integer chauffRe) {
        this.chauffRe = chauffRe;
    }


    public Agent getChauffPr() {
        return chauffPr;
    }

    public void setChauffPr(Agent chauffPr) {
        this.chauffPr = chauffPr;
    }

    public Agent getRecPr() {
        return RecPr;
    }

    public void setRecPr(Agent recPr) {
        RecPr = recPr;
    }

    public Integer getRecRe() {
        return RecRe;
    }

    public void setRecRe(Integer recRe) {
        RecRe = recRe;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Date getTimeDepartR() {
        return timeDepartR;
    }

    public void setTimeDepartR(Date timeDepartR) {
        this.timeDepartR = timeDepartR;
    }

    public Date getTimeArriveR() {
        return timeArriveR;
    }

    public void setTimeArriveR(Date timeArriveR) {
        this.timeArriveR = timeArriveR;
    }

    public Integer getvMax() {
        return vMax;
    }

    public void setvMax(Integer vMax) {
        this.vMax = vMax;
    }

    public Integer getAvanceRetard() {
        return avanceRetard;
    }

    public void setAvanceRetard(Integer avanceRetard) {
        this.avanceRetard = avanceRetard;
    }

    public Integer getChangement() {
        return changement;
    }

    public void setChangement(Integer changement) {
        this.changement = changement;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Integer getDeValid() {
        return deValid;
    }

    public void setDeValid(Integer deValid) {
        this.deValid = deValid;
    }

    public Integer getAlert() {
        return alert;
    }

    public void setAlert(Integer alert) {
        this.alert = alert;
    }

    public DrVehicule getBusPr() {
        return busPr;
    }

    public void setBusPr(DrVehicule busPr) {
        this.busPr = busPr;
    }

    public DrVehicule getBusRe() {
        return busRe;
    }

    public void setBusRe(DrVehicule busRe) {
        this.busRe = busRe;
    }

    public TripsSql(TripsId tripsId, Integer serviceId, Integer directionId, Date timeDepart, Integer haveret,
            String timeNret, Integer tripNid, Integer grp, Integer chauffRe,
             Integer recRe, Integer etat, Date timeDepartR, Date timeArriveR, Integer vMax,
            Integer avanceRetard, Integer changement, String metaData, Integer deValid, Integer alert) {
        this.tripsId = tripsId;
        this.serviceId = serviceId;
        this.directionId = directionId;
        this.timeDepart = timeDepart;
        this.haveret = haveret;
        this.timeNret = timeNret;
        this.tripNid = tripNid;
        this.grp = grp;
        this.chauffRe = chauffRe;
        RecRe = recRe;
        this.etat = etat;
        this.timeDepartR = timeDepartR;
        this.timeArriveR = timeArriveR;
        this.vMax = vMax;
        this.avanceRetard = avanceRetard;
        this.changement = changement;
        this.metaData = metaData;
        this.deValid = deValid;
        this.alert = alert;
    }

    public TripsSql(){}

    public void setLigne(DrLigne ligne) {
        this.ligne = ligne;
    }

}
