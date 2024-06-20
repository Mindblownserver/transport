package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class SHAPS {
    @Id
    @Column(name="DENUMLI")
    private Long DENUMLI;
    @Column(name="N")
    private Integer N;
    @Column(name="LAT")
    private Integer LAT;
    @Column(name="LON")
    private Long LON;
    @Column(name="MAX_VITESSE")
    private Integer MAX_VITESSE;
    @Column(name="KM")
    private Integer KM;
    @Column(name="DIRECTION_ID")
    private Long DIRECTION_ID;
    public Long getDENUMLI() {
        return DENUMLI;
    }
    public void setDENUMLI(Long dENUMLI) {
        DENUMLI = dENUMLI;
    }
    public Integer getN() {
        return N;
    }
    public void setN(Integer n) {
        N = n;
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
    public Long getDIRECTION_ID() {
        return DIRECTION_ID;
    }
    public void setDIRECTION_ID(Long dIRECTION_ID) {
        DIRECTION_ID = dIRECTION_ID;
    }
    
}
