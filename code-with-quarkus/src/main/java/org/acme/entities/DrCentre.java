package org.acme.entities;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DrCentre extends PanacheEntityBase {
    @Id
    @Column(name="DECCENT")
    private Long decCentre;
    
    @Column(name="DELCENT")
    private String delCent;
    
    @Column(name="AR_DELCENT")
    private String arDelCent;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "centre")
    private List<DrLigne> lignes;

    public Long getDeccentre() {
        return decCentre;
    }
    public void setDec_centre(Long decCentre) {
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

    
    public DrCentre(Long decCentre, String delCent, String arDelCent) {
        this.decCentre = decCentre;
        this.delCent = delCent;
        this.arDelCent = arDelCent;
    }
    public DrCentre() {
    }
    
}
