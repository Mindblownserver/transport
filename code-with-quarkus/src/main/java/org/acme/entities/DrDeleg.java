package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DrDeleg {
    @Id
    @Column(name="DECDELEG")
    private Long decDeleg;
    @Column(name="LIBDELEGAR")
    private String libDelegAr;
    @Column(name="LIBDELEGFR")
    private String libDelegFr;
    public Long getDec_deleg() {
        return decDeleg;
    }
    public void setDec_deleg(Long decDeleg) {
        this.decDeleg = decDeleg;
    }
    public String getAr_deleg() {
        return libDelegAr;
    }
    public void setAr_deleg(String libDelegAr) {
        this.libDelegAr = libDelegAr;
    }
    public String getFr_deleg() {
        return libDelegFr;
    }
    public void setFr_deleg(String fr_deleg) {
        this.libDelegFr = fr_deleg;
    }// 84651
    public DrDeleg(Long dec_deleg, String ar_deleg, String fr_deleg) {
        this.decDeleg = dec_deleg;
        this.libDelegAr = ar_deleg;
        this.libDelegFr = fr_deleg;
    }
    public DrDeleg() {
    }
    
}
