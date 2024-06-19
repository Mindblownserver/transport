package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DrDeleg {
    @Id
    @Column(name="DECDELEG")
    private Long dec_deleg;
    @Column(name="LIBDELEGAR")
    private String ar_deleg;
    @Column(name="LIBDELEGFR")
    private String fr_deleg;
    public Long getDec_deleg() {
        return dec_deleg;
    }
    public void setDec_deleg(Long dec_deleg) {
        this.dec_deleg = dec_deleg;
    }
    public String getAr_deleg() {
        return ar_deleg;
    }
    public void setAr_deleg(String ar_deleg) {
        this.ar_deleg = ar_deleg;
    }
    public String getFr_deleg() {
        return fr_deleg;
    }
    public void setFr_deleg(String fr_deleg) {
        this.fr_deleg = fr_deleg;
    }
    public DrDeleg(Long dec_deleg, String ar_deleg, String fr_deleg) {
        this.dec_deleg = dec_deleg;
        this.ar_deleg = ar_deleg;
        this.fr_deleg = fr_deleg;
    }
    public DrDeleg() {
    }
    
}
