package org.acme.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DrStati {
    @Id
    @Column(name="DECSTAT")
    private Long decStat;

    @Column(name="DELSTAT")
    private String nomFr;

    @Column(name="DELSTAA")
    private String nomAr;

    @Column(name="DECTYST")
    private int typeSt;

    @Column(name="STOP_LAT")
    private float latitude;

    @Column(name="STOP_LON")
    private float longetude;

    @Column(name="DATE_SYS")
    private Date date;

    @Column(name="RAYON")
    private int rayon;

    public Long getDecStat() {
        return decStat;
    }

    public void setDecStat(Long decStat) {
        this.decStat = decStat;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public int getTypeSt() {
        return typeSt;
    }

    public void setTypeSt(int typeSt) {
        this.typeSt = typeSt;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongetude() {
        return longetude;
    }

    public void setLongetude(float longetude) {
        this.longetude = longetude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public DrStati() {
    }

    public DrStati(Long decStat, String nomFr, String nomAr, int typeSt, float latitude, float longetude, Date date,
            int rayon) {
        this.decStat = decStat;
        this.nomFr = nomFr;
        this.nomAr = nomAr;
        this.typeSt = typeSt;
        this.latitude = latitude;
        this.longetude = longetude;
        this.date = date;
        this.rayon = rayon;
    }
    
    
}
