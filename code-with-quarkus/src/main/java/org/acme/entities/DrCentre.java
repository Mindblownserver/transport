package org.acme.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DrCentre {
    @Id
    @Column(name="DECCENT")
    private Long dec_centre;
    
    @Column(name="DELCENT")
    private String del_centre;
    
    @Column(name="AR_DELCENT")
    private String ar_centre;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "centre")
    private List<DrLigne> lignes;

    public Long getDec_centre() {
        return dec_centre;
    }
    public void setDec_centre(Long dec_centre) {
        this.dec_centre = dec_centre;
    }
    public String getDel_centre() {
        return del_centre;
    }
    public void setDel_centre(String del_centre) {
        this.del_centre = del_centre;
    }
    public String getAr_centre() {
        return ar_centre;
    }
    public void setAr_centre(String ar_centre) {
        this.ar_centre = ar_centre;
    }
    public DrCentre(Long dec_centre, String del_centre, String ar_centre) {
        this.dec_centre = dec_centre;
        this.del_centre = del_centre;
        this.ar_centre = ar_centre;
    }
    public DrCentre() {
    }
    
}
