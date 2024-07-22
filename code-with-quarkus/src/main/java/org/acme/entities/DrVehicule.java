package org.acme.entities;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name="DECCENT")
    private DrCentre centre;

    @ManyToOne
    @JoinColumn(name="DecDeleg")
    private DrDeleg decdeleg;

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

    public DrCentre getDeccent() {
        return centre;
    }

    public void setDeccent(DrCentre deccent) {
        this.centre = deccent;
    }

    public DrDeleg getDeleg() {
        return decdeleg;
    }

    public void setDeleg(DrDeleg decdeleg) {
        this.decdeleg = decdeleg;
    }
    public Long getDecodvh() {
        return decodvh;
    }

    public void setDecodvh(Long decodvh) {
        this.decodvh = decodvh;
    }

    public DrVehicule() {
    }
}
