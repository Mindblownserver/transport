package org.acme.entities;

import org.acme.Embeddable.DrItinIdRecord;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DrItin {
    @EmbeddedId
    private DrItinIdRecord id;

    @Column(name="DEKMSTA")
    private Integer kmsTa;

    @Column(name="DEDURTR")
    private Integer dureetr;

    @Column(name="DEESCALE", nullable = true)
    private Integer escale;

    @Column(name="DEDATES")
    private String dates; // peut ne pas être avec les horraires
    
    @Column(name="DETARIF")
    private Integer tarif;

    @ManyToOne
    @JoinColumn(name="DECSTAT")
    @JsonBackReference
    private DrStati station;

    public DrItinIdRecord getId() {
        return id;
    }

    public void setId(DrItinIdRecord id) {
        this.id = id;
    }

    public Integer getKmsTa() {
        return kmsTa;
    }

    public void setKmsTa(Integer kmsTa) {
        this.kmsTa = kmsTa;
    }

    public Integer getDureetr() {
        return dureetr;
    }

    public void setDureetr(Integer dureetr) {
        this.dureetr = dureetr;
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

    public DrStati getStation() {
        return station;
    }

    public void setStation(DrStati station) {
        this.station = station;
    }

    public DrItin() {
    }

    public DrItin(DrItinIdRecord id, Integer kmsTa, Integer dureetr, Integer escale, String dates, Integer tarif, DrStati station) {
        this.id = id;
        this.kmsTa = kmsTa;
        this.dureetr = dureetr;
        this.escale = escale;
        this.dates = dates;
        this.tarif = tarif;
        this.station = station;
    }

    
}