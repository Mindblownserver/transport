package org.acme.entities;

import org.acme.Embeddable.ShapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name="SHAPE")
public class SHAPS {
    @EmbeddedId
    private ShapsId idShaps;
    @Column(name="LAT")
    private Integer LAT;
    @Column(name="LON")
    private Long LON;
    @Column(name="MAX_VITESSE")
    private Integer MAX_VITESSE;
    @Column(name="KM")
    private Integer KM;

    @JoinColumn(name="DENUMLI", insertable=false, updatable=false)
    @ManyToOne
    @MapsId("numLigne")
    private DrLigne ligne;

    public ShapsId getIdShaps() {
        return idShaps;
    }
    public void setIdShaps(ShapsId idShaps) {
        this.idShaps = idShaps;
    }
    public Integer getLAT() {
        return LAT;
    }
    public void setLAT(Integer lAT) {
        LAT = lAT;
    }
    public Long getLON() {
        return LON;
    }
    public void setLON(Long lON) {
        LON = lON;
    }
    public Integer getMAX_VITESSE() {
        return MAX_VITESSE;
    }
    public void setMAX_VITESSE(Integer mAX_VITESSE) {
        MAX_VITESSE = mAX_VITESSE;
    }
    public Integer getKM() {
        return KM;
    }
    public void setKM(Integer kM) {
        KM = kM;
    }
    public SHAPS() {
    }
    public SHAPS(ShapsId idShaps, Integer lAT, Long lON, Integer mAX_VITESSE, Integer kM) {
        this.idShaps = idShaps;
        LAT = lAT;
        LON = lON;
        MAX_VITESSE = mAX_VITESSE;
        KM = kM;
    }
}
