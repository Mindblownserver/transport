package org.acme.entities;

public class DrDeleg{
    
    
    private int decDeleg;
    
    
    private String libDelegAr;
    
    
    private String libDelegFr;

    

    public int getDecdeleg() {
        return decDeleg;
    }
    public void setDec_deleg(int decDeleg) {
        this.decDeleg = decDeleg;
    }
    public String getArdeleg() {
        return libDelegAr;
    }
    public void setAr_deleg(String libDelegAr) {
        this.libDelegAr = libDelegAr;
    }
    public String getFrdeleg() {
        return libDelegFr;
    }
    public void setFr_deleg(String libDelegFr) {
        this.libDelegFr = libDelegFr;
    }
    public DrDeleg(int decDeleg, String libDelegAr, String libDelegFr) {
        this.decDeleg = decDeleg;
        this.libDelegAr = libDelegAr;
        this.libDelegFr = libDelegFr;
    }
    public DrDeleg() {
    }
    
}
