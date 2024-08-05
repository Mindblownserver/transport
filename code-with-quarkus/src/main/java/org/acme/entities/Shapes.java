package org.acme.entities;

import org.acme.Embeddable.ShapsId;


public class Shapes{
    
    private ShapsId idShaps;
    
    private double lat;
    
    private double lon;
    
    private Integer maxVitesse;
    
    private Integer km;
 
    
    public ShapsId getIdShaps() {
        return idShaps;
    }
    public void setIdShaps(ShapsId idShaps) {
        this.idShaps = idShaps;
    }
    public double getLAT() {
        return lat;
    }
    public void setLAT(double lat) {
        this.lat = lat;
    }
    public double getLON() {
        return lon;
    }
    public void setLON(double lON) {
        lon = lON;
    }
    public Integer getMAX_VITESSE() {
        return maxVitesse;
    }
    public void setMAX_VITESSE(Integer mAX_VITESSE) {
        maxVitesse = mAX_VITESSE;
    }
    public Integer getKM() {
        return km;
    }
    public void setKM(Integer kM) {
        km = kM;
    }
    public Shapes() {
    }
    public Shapes(ShapsId idShaps, double lat, double lON, Integer mAX_VITESSE, Integer kM) {
        this.idShaps = idShaps;
        this.lat = lat;
        lon = lON;
        maxVitesse = mAX_VITESSE;
        km = kM;
    }
}
