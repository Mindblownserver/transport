package org.acme.entities;

import org.acme.Embeddable.DrItinIdRecord;


public class DrItin{
    
    private DrItinIdRecord id;
    
    private Integer deKmsTa;
 
    private Integer deDureeTr;

    private Integer deEscale;

    private String deDates; // peut ne pas être avec les horraires
    
    private Integer deTarif;

    private DrStati stat;

    private DrStati station;

    
    

    public DrItinIdRecord getId() {
        return id;
    }

    public DrStati getStat() {
        return stat;
    }

    public void setStat(DrStati stat) {
        this.stat = stat;
    }

    public void setId(DrItinIdRecord id) {
        this.id = id;
    }

    public Integer getKmsTa() {
        return deKmsTa;
    }

    public void setKmsTa(Integer deKmsTa) {
        this.deKmsTa = deKmsTa;
    }

    public Integer getDureetr() {
        return deDureeTr;
    }

    public void setDureetr(Integer deDureeTr) {
        this.deDureeTr = deDureeTr;
    }

    public Integer getEscale() {
        return deEscale;
    }

    public void setEscale(Integer deEscale) {
        this.deEscale = deEscale;
    }

    public String getDates() {
        return deDates;
    }

    public void setDates(String deDates) {
        this.deDates = deDates;
    }

    public Integer getTarif() {
        return deTarif;
    }

    public void setTarif(Integer deTarif) {
        this.deTarif = deTarif;
    }

    public DrStati getStation() {
        return station;
    }

    public void setStation(DrStati station) {
        this.station = station;
    }
    

    public DrItin() {
    }

    public DrItin(DrItinIdRecord id, Integer deKmsTa, Integer deDureeTr, Integer deEscale, String deDates, Integer deTarif, DrStati station) {
        this.id = id;
        this.deKmsTa = deKmsTa;
        this.deDureeTr = deDureeTr;
        this.deEscale = deEscale;
        this.deDates = deDates;
        this.deTarif = deTarif;
        this.station = station;
    }

    
}