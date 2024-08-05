package org.acme.entities;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


public class DrCentre  {
    
    
    private int decCentre;
    
    
    private String delCent;
    
    
    private String arDelCent;

    

    public int getDeccentre() {
        return decCentre;
    }
    public void setDec_centre(int decCentre) {
        this.decCentre = decCentre;
    }
    public String getDelcentre() {
        return delCent;
    }
    public void setDel_centre(String delCent) {
        this.delCent = delCent;
    }
    public String getArcentre() {
        return arDelCent;
    }
    public void setAr_centre(String arDelCent) {
        this.arDelCent = arDelCent;
    }

    
    public DrCentre(int decCentre, String delCent, String arDelCent) {
        this.decCentre = decCentre;
        this.delCent = delCent;
        this.arDelCent = arDelCent;
    }
    public DrCentre() {
    }
    
}
