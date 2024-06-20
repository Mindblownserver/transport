package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class DrStati {
    @Id
    @Column(name="DECSTAT")
    private Integer stat;
    @Column(name="DELSTAT")
    private String nomfr;
    @Column(name="DELSTAA")
    private String nomAr;
    @Column(name="DECTYST")
    private Integer tyst;
    @Column(name="STOP_LAT")
    private Float stopLat;
    @Column(name="STOP_LON")
    private Float stopLon;
    @Column(name="DATE_SYS")
    private Date dateSys;
    @Column(name="RAYON")
    private Integer rayon;

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getNomfr() {
        return nomfr;
    }

    public void setNomfr(String nomfr) {
        this.nomfr = nomfr;
    }

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public Integer getTyst() {
        return tyst;
    }

    public void setTyst(Integer tyst) {
        this.tyst = tyst;
    }

    public Float getStopLat() {
        return stopLat;
    }

    public void setStopLat(Float stopLat) {
        this.stopLat = stopLat;
    }

    public Float getStopLon() {
        return stopLon;
    }

    public void setStopLon(Float stopLon) {
        this.stopLon = stopLon;
    }

    public Date getDateSys() {
        return dateSys;
    }

    public void setDateSys(Date dateSys) {
        this.dateSys = dateSys;
    }

    public Integer getRayon() {
        return rayon;
    }

    public void setRayon(Integer rayon) {
        this.rayon = rayon;
    }

    public DrStati(Integer stat, String nomfr, String nomAr, Integer tyst, Float stopLat, Float stopLon, Date dateSys, Integer rayon) {
        this.stat = stat;
        this.nomfr = nomfr;
        this.nomAr = nomAr;
        this.tyst = tyst;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.dateSys = dateSys;
        this.rayon = rayon;
    }

    public DrStati(){}
}
