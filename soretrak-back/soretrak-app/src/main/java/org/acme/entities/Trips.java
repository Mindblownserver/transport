package org.acme.entities;

import jakarta.persistence.*;
import org.acme.Embeddable.TripsId;

import java.util.Date;
import java.util.List;

@Entity
public class Trips {
    @EmbeddedId
    private TripsId tripsId;
    @Column(name="SERVICE_ID")
    private Integer serviceId;
    @Column(name="DIRECTION_ID")
    private Integer directionId;
    @Column(name="TIME_DEPART")
    private Date timeDepart;
    @Column(name="HAVERET")
    private Integer haveret;
    @Column(name="TIME_NRET")
    private String timeNret;
    @Column(name="TRIP_NID")
    private Integer tripNid;
    @Column(name="GRP")
    private Integer grp;
    @Column(name="BUS_RE")
    private Integer busRe;
    @Column(name="CHAUFF_PR")
    private Integer chauffPr;
    @Column(name="CHAUFF_RE")
    private Integer chauffRe;
    @Column(name="REC_PR")
    private Integer RecPr;
    @Column(name="REC_RE")
    private Integer RecRe;
    @Column(name="ETAT")
    private Integer etat;
    @Column(name="TIME_DEPART_R")
    private Date timeDepartR;
    @Column(name="TIME_ARRIVE_R")
    private Date timeArriveR;
    @Column(name="VMAX")
    private Integer vMax;
    @Column(name="AVANCE_RETARD")
    private Integer avanceRetard;
    @Column(name="CHANGEMENT")
    private Integer changement;
    @Column(name="META_DATA")
    private String metaData;
    @Column(name="DEVALID")
    private Integer deValid;
    @Column(name="ALERT")
    private Integer alert;
    @JoinColumn(name="DENUMLI", insertable=false, updatable=false)

    @ManyToOne
    private DrLigne ligne;
    @Transient  
    public Date finalStopTime;
    @ManyToOne
    @JoinColumn(name="BUS_PR")
    private Bus busPr;

    @OneToMany(mappedBy = "trip")
    public List<StopTimes> stopTimesList;

    // for info to be exposed into your JSON response, you need to prepare their getters & setters(optional)
    public Bus getBusPr() {
        return busPr;
    }

    public void setBusPr(Bus busPr) {
        this.busPr = busPr;
    }

    public Date getFinalStopTime() {
        return finalStopTime;
    }

    public void setFinalStopTime(Date finalStopTime) {
        this.finalStopTime = finalStopTime;
    }

    public TripsId getTripsId() {
        return tripsId;
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

    public Integer getBusRe() {
        return busRe;
    }

    public void setBusRe(Integer busRe) {
        this.busRe = busRe;
    }

    public Integer getChauffPr() {
        return chauffPr;
    }

    public void setChauffPr(Integer chauffPr) {
        this.chauffPr = chauffPr;
    }

    public Integer getChauffRe() {
        return chauffRe;
    }

    public void setChauffRe(Integer chauffRe) {
        this.chauffRe = chauffRe;
    }

    public Integer getRecPr() {
        return RecPr;
    }

    public void setRecPr(Integer recPr) {
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

    public Trips(TripsId tripsId, Integer serviceId, Integer directionId, Date timeDepart, Integer haveret, String timeNret, Integer tripNid, Integer grp, Bus busPr, Integer busRe, Integer chauffPr, Integer chauffRe, Integer recPr, Integer recRe, Integer etat, Date timeDepartR, Date timeArriveR, Integer vMax, Integer avanceRetard, Integer changement, String metaData, Integer deValid, Integer alert) {
        this.tripsId = tripsId;
        this.serviceId = serviceId;
        this.directionId = directionId;
        this.timeDepart = timeDepart;
        this.haveret = haveret;
        this.timeNret = timeNret;
        this.tripNid = tripNid;
        this.grp = grp;
        this.busPr = busPr;
        this.busRe = busRe;
        this.chauffPr = chauffPr;
        this.chauffRe = chauffRe;
        RecPr = recPr;
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
    public Trips(){}
}
