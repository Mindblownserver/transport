package org.acme.entities.SQL;

import jakarta.persistence.*;

import org.acme.entities.DrCentre;
import org.acme.entities.DrDeleg;

public class DrVehiculeSql {
    private Long decodvh;
    private String dematri;
    private int decatvh;
    private int centre;
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
        return centre;
    }

    public void setDeccent(int deccent) {
        this.centre = deccent;
    }

    public int getDecdeleg() {
        return decdeleg;
    }

    public void setDecdeleg(int decdeleg) {
        this.decdeleg = decdeleg;
    }

    public Long getDecodvh() {
        return decodvh;
    }

    public void setDecodvh(Long decodvh) {
        this.decodvh = decodvh;
    }

    public DrVehiculeSql() {
    }
}
