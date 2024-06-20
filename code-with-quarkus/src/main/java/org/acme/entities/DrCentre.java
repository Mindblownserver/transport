package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DrCentre {
    @Id
    @Column(name="DECCENT")
    private Long dec_centre;
    @Column(name="DELCENT")
    private String del_centre;
    @Column(name="AR_DELCENT")
    private String ar_centre;
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
