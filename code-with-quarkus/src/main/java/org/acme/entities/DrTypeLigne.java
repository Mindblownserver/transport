package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


public class DrTypeLigne extends PanacheEntityBase{
    
    
    private int decTyLi;
    
    private String delTyLA;
    
    private String delTyLi;

    public int getDec_tpli() {
        return decTyLi;
    }
    public void setDec_tpli(int decTyLi) {
        this.decTyLi = decTyLi;
    }
    public String getAr_tpli() {
        return delTyLA;
    }
    public void setAr_tpli(String delTyLA) {
        this.delTyLA = delTyLA;
    }
    public String getFr_tpli() {
        return delTyLi;
    }
    public void setFr_tpli(String delTyLi) {
        this.delTyLi = delTyLi;
    }
    public DrTypeLigne() {
    }
}
