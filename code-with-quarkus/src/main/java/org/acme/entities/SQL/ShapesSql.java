package org.acme.entities.SQL;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.acme.Embeddable.ShapsId;
import org.acme.entities.DrLigne;


public class ShapesSql extends PanacheEntityBase{
    private Long deNumLi;
    private Integer N;
    private Long DIRECTION_ID;
    private Long lat;
    private Long lon;
    private Integer maxVitesse;
    private Integer km;
    private DrLigne ligne;

    public Long getDeNumLi() {
        return deNumLi;
    }

    public void setDeNumLi(Long deNumLi) {
        this.deNumLi = deNumLi;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Long getDIRECTION_ID() {
        return DIRECTION_ID;
    }

    public void setDIRECTION_ID(Long DIRECTION_ID) {
        this.DIRECTION_ID = DIRECTION_ID;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Integer getMaxVitesse() {
        return maxVitesse;
    }

    public void setMaxVitesse(Integer maxVitesse) {
        this.maxVitesse = maxVitesse;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public DrLigne getLigne() {
        return ligne;
    }

    public void setLigne(DrLigne ligne) {
        this.ligne = ligne;
    }

    public ShapesSql() {
    }

    public ShapesSql(Long deNumLi, Integer n, Long DIRECTION_ID, Long lat, Long lon, Integer maxVitesse, Integer km, DrLigne ligne) {
        this.deNumLi = deNumLi;
        N = n;
        this.DIRECTION_ID = DIRECTION_ID;
        this.lat = lat;
        this.lon = lon;
        this.maxVitesse = maxVitesse;
        this.km = km;
        this.ligne = ligne;
    }
}
