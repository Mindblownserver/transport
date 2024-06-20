package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DrItin {
    @Id
    @Column(name="DENUMLI")
    private Integer numLigne;
    @Column(name="DENUMLG")
    private Integer numlg;
    @Column(name="DECSTAT")
    private Integer stat;
    @Column(name="DEKMSTA")
    private Integer kmsta;
    @Column(name="DEDURTR")
    private Integer durtr;
    @Column(name="DEESCALE")
    private Integer escale;
    @Column(name="DEDATES")
    private String dates;
    @Column(name="DETARIF", nullable = true)
    private Integer tarif;

    public Integer getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(Integer numLigne) {
        this.numLigne = numLigne;
    }

    public Integer getNumlg() {
        return numlg;
    }

    public void setNumlg(Integer numlg) {
        this.numlg = numlg;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getKmsta() {
        return kmsta;
    }

    public void setKmsta(Integer kmsta) {
        this.kmsta = kmsta;
    }

    public Integer getDurtr() {
        return durtr;
    }

    public void setDurtr(Integer durtr) {
        this.durtr = durtr;
    }

    public Integer getEscale() {
        return escale;
    }

    public void setEscale(Integer escale) {
        this.escale = escale;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    public DrItin(Integer numLigne, Integer numlg, Integer stat, Integer kmsta, Integer durtr, Integer escale, String dates, Integer tarif) {
        this.numLigne = numLigne;
        this.numlg = numlg;
        this.stat = stat;
        this.kmsta = kmsta;
        this.durtr = durtr;
        this.escale = escale;
        this.dates = dates;
        this.tarif = tarif;
    }

    public DrItin(){}
}
