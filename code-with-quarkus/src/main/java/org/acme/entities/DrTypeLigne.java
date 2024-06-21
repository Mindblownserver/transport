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
    private Long decTyLi;
    @Column(name="DELTYLA")
    private String delTyLA;
    @Column(name="DELTYLI")
    private String delTyLi;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="type")
    private List<DrLigne> lignes;

    public Long getDec_tpli() {
        return decTyLi;
    }
    public void setDec_tpli(Long decTyLi) {
        this.decTyLi = decTyLi;
    }
    public String getAr_tpli() {
        return delTyLA;
    }
    public void setAr_tpli(String delTyLA) {
        this.delTyLA = delTyLA;
    }
    public String getFr_tpli() {
        return delTyLi;
    }
    public void setFr_tpli(String delTyLi) {
        this.delTyLi = delTyLi;
    }
    public DrTypeLigne() {
    }
}
