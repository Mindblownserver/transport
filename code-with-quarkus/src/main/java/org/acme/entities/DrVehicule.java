package org.acme.entities;

public class DrVehicule {

    private int decodvh;

    private String dematri;

    private int deccent;
      
    private int decdeleg;
    
    private TypeVehicule decatvh;
   
    public String getDematri() {
        return dematri;
    }

    public void setDematri(String dematri) {
        this.dematri = dematri;
    }

    public TypeVehicule getDecatvh() {
        return decatvh;
    }

    public void setDecatvh(TypeVehicule decatvh) {
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
    
    public int getDecodvh() {
        return decodvh;
    }
    
    public void setDecodvh(int decodvh) {
        this.decodvh = decodvh;
    }
    
    public DrVehicule() {
    }
    
}
