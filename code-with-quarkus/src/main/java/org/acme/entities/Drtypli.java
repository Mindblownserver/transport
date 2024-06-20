package org.acme.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Drtypli {
    @Id
    @Column(name="DECTYLI")
    private Long dec_tpli;
    @Column(name="DELTYLA")
    private String ar_tpli;
    @Column(name="DELTYLI")
    private String fr_tpli;
    public Long getDec_tpli() {
        return dec_tpli;
    }
    public void setDec_tpli(Long dec_tpli) {
        this.dec_tpli = dec_tpli;
    }
    public String getAr_tpli() {
        return ar_tpli;
    }
    public void setAr_tpli(String ar_tpli) {
        this.ar_tpli = ar_tpli;
    }
    public String getFr_tpli() {
        return fr_tpli;
    }
    public void setFr_tpli(String fr_tpli) {
        this.fr_tpli = fr_tpli;
    }
    public Drtypli() {
    }
    public Drtypli(Long dec_tpli, String ar_tpli, String fr_tpli) {
        this.dec_tpli = dec_tpli;
        this.ar_tpli = ar_tpli;
        this.fr_tpli = fr_tpli;
    }
    

    
}
