package org.acme.entities.SQL;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.acme.entities.DrItin;
import org.acme.entities.StopTimes;

import java.util.Date;
import java.util.List;


public class DrStatiSql extends PanacheEntityBase {
    private Long decStat;
    private String delStat;
    private String delStaA;
    private int decTySt;
    private Float stopLat;
    private Float stopLon;
    private Date dateSys;
    private int rayon;
    private List<DrItinSql> itineraires;
    private List<StopTimes> stopTimesList;

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

    public DrStatiSql() {
    }

    public DrStatiSql(Long decStat, String delStat, String delStaA, int decTySt, Float stopLat, Float stopLon, Date dateSys,
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
