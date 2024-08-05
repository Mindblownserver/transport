package org.acme.entities;



public class TypeVehicule {

    private int id;
   
    private String decateg;
   
    private String deacate;

    private int denbplc;
    
    private Long dureeVie;
    
    private Float prixKm;

    private Long prixIm;

    private String color;


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecateg() {
        return decateg;
    }

    public void setDecateg(String decateg) {
        this.decateg = decateg;
    }

    public String getDeacate() {
        return deacate;
    }

    public void setDeacate(String deacate) {
        this.deacate = deacate;
    }

    public Integer getDenbplc() {
        return denbplc;
    }

    public void setDenbplc(Integer denbplc) {
        this.denbplc = denbplc;
    }

    public Long getDureeVie() {
        return dureeVie;
    }

    public void setDureeVie(Long dureeVie) {
        this.dureeVie = dureeVie;
    }

    public Float getPrixKm() {
        return prixKm;
    }

    public void setPrixKm(Float prixKm) {
        this.prixKm = prixKm;
    }

    public Long getPrixIm() {
        return prixIm;
    }

    public void setPrixIm(Long prixIm) {
        this.prixIm = prixIm;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public TypeVehicule() {
    }
    
    
}
