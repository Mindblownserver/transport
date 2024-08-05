package org.acme.entities;

import java.util.Date;

public class DrStati {
    
    private int decStat;

    
    private String delStat;

    
    private String delStaA;

    
    private int decTySt;

    
    private Float stopLat;

    
    private Float stopLon;

    
    private Date dateSys;

    
    private int rayon;

    public int getDecStat() {
        return decStat;
    }

    public void setDecStat(int decStat) {
        this.decStat = decStat;
    }

    public String getNomFr() {
        return delStat;
    }

    public void setNomFr(String delStat) {
        this.delStat = delStat;
    }

    public String getNomAr() {
        return delStaA;
    }

    public void setNomAr(String delStaA) {
        this.delStaA = delStaA;
    }

    public int getTypeSt() {
        return decTySt;
    }

    public void setTypeSt(int decTySt) {
        this.decTySt = decTySt;
    }

    public Float getLatitude() {
        return stopLat;
    }

    public void setLatitude(Float stopLat) {
        this.stopLat = stopLat;
    }

    public Float getLongetude() {
        return stopLon;
    }

    public void setLongetude(Float stopLon) {
        this.stopLon = stopLon;
    }

    public Date getDate() {
        return dateSys;
    }

    public void setDate(Date dateSys) {
        this.dateSys = dateSys;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public DrStati() {
    }

    public DrStati(int decStat, String delStat, String delStaA, int decTySt, Float stopLat, Float stopLon, Date dateSys,
            int rayon) {
        this.decStat = decStat;
        this.delStat = delStat;
        this.delStaA = delStaA;
        this.decTySt = decTySt;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.dateSys = dateSys;
        this.rayon = rayon;
    }
    
    
}
