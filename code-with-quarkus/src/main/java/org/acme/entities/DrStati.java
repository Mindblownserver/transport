package org.acme.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DrStati extends PanacheEntityBase {
    @Id
    @Column(name="DECSTAT")
    private Long decStat;

    @Column(name="DELSTAT")
    private String delStat;

    @Column(name="DELSTAA")
    private String delStaA;

    @Column(name="DECTYST")
    private int decTySt;

    @Column(name="STOP_LAT")
    private Float stopLat;

    @Column(name="STOP_LON")
    private Float stopLon;

    @Column(name="DATE_SYS")
    private Date dateSys;

    @Column(name="RAYON")
    private int rayon;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<DrItin> itineraires;

    public Long getDecStat() {
        return decStat;
    }

    public void setDecStat(Long decStat) {
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

    public DrStati(Long decStat, String delStat, String delStaA, int decTySt, Float stopLat, Float stopLon, Date dateSys,
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
