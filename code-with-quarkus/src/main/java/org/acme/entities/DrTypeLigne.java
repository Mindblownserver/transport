package org.acme.entities;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="DRTYPELI")
public class DrTypeLigne {
    @Id
    @Column(name="DECTYLI")
    private Long numType;
    @Column(name="DELTYLA")
    private String nomAr;
    @Column(name="DELTYLI")
    private String nomFr;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="type")
    private List<DrLigne> lignes;

    public Long getDec_tpli() {
        return numType;
    }
    public void setDec_tpli(Long numType) {
        this.numType = numType;
    }
    public String getAr_tpli() {
        return nomAr;
    }
    public void setAr_tpli(String nomAr) {
        this.nomAr = nomAr;
    }
    public String getFr_tpli() {
        return nomFr;
    }
    public void setFr_tpli(String nomFr) {
        this.nomFr = nomFr;
    }
    public DrTypeLigne() {
    }
}
