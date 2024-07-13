package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DRVEHIC")
public class DrVehicule {

    @Id
    @Column(name = "DECODVH", nullable = false)
    private Long decodvh;

    @Column(name = "DEMATRI", length = 26)
    private String dematri;

    @Column(name = "DECATVH")
    private int decatvh;

    @Column(name = "DECCENT")
    private int deccent;

    @Column(name = "DECDELEG")
    private int decdeleg;

    public String getDematri() {
        return dematri;
    }

    public void setDematri(String dematri) {
        this.dematri = dematri;
    }

    public int getDecatvh() {
        return decatvh;
    }

    public void setDecatvh(int decatvh) {
        this.decatvh = decatvh;
    }

    public int getDeccent() {
        return deccent;
    }

    public void setDeccent(int deccent) {
        this.deccent = deccent;
    }

    public int getDecdeleg() {
        return decdeleg;
    }

    public void setDecdeleg(int decdeleg) {
        this.decdeleg = decdeleg;
    }

    public DrVehicule() {
    }

    public Long getDecodvh() {
        return decodvh;
    }

    public void setDecodvh(Long decodvh) {
        this.decodvh = decodvh;
    }

    

}
