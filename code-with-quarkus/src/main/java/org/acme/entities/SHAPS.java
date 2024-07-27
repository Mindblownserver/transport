package org.acme.entities;

import org.acme.Embeddable.ShapsId;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name="SHAPE")
public class SHAPS extends PanacheEntityBase{
    @EmbeddedId
    private ShapsId idShaps;
    @Column(name="LAT")
    private double lat;
    @Column(name="LON")
    private double lon;
    @Column(name="MAX_VITESSE")
    private Integer maxVitesse;
    @Column(name="KM")
    private Integer km;
 
    @JoinColumn(name="deNumLi", insertable=false, updatable=false)
    @ManyToOne
    @MapsId("deNumLi")
    private DrLigne ligne;

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
    public SHAPS() {
    }
    public SHAPS(ShapsId idShaps, double lat, double lON, Integer mAX_VITESSE, Integer kM) {
        this.idShaps = idShaps;
        this.lat = lat;
        lon = lON;
        maxVitesse = mAX_VITESSE;
        km = kM;
    }
}
