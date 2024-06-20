package org.acme.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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
    private Float latitude;

    @Column(name="STOP_LON")
    private Float longetude;

    @Column(name="DATE_SYS")
    private Date date;

    @Column(name="RAYON")
    private int rayon;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DrItin> itineraires;

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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongetude() {
        return longetude;
    }

    public void setLongetude(Float longetude) {
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

    public DrStati(Long decStat, String nomFr, String nomAr, int typeSt, Float latitude, Float longetude, Date date,
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
