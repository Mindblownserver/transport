package org.acme.entities;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DrDeleg extends PanacheEntityBase{
    @Id
    @Column(name="DECDELEG")
    private Long decDeleg;
    
    @Column(name="LIBDELEGAR")
    private String libDelegAr;
    
    @Column(name="LIBDELEGFR")
    private String libDelegFr;

    @OneToMany(mappedBy = "deleg")
    private List<DrLigne> lignes;

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
    public void setFr_deleg(String libDelegFr) {
        this.libDelegFr = libDelegFr;
    }
    public DrDeleg(Long decDeleg, String libDelegAr, String libDelegFr) {
        this.decDeleg = decDeleg;
        this.libDelegAr = libDelegAr;
        this.libDelegFr = libDelegFr;
    }
    public DrDeleg() {
    }
    
}
