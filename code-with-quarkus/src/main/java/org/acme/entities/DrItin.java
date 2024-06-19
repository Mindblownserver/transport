package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class DrItin {
    @EmbeddedId
    private DrItinId id;

    @Column(name="DEKMSTA")
    private int kmsTa;

    @Column(name="DEDURTR")
    private int dureetr;

    @Column(name="DEESCALE")
    private int escale;

    @Column(name="DEDATES")
    private String dates; // peut ne pas être avec les horraires
    
    @Column(name="DETARIF")
    private int tarif;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name="DECSTAT")
    private DrStati station;

    public DrItinId getId() {
        return id;
    }

    public void setId(DrItinId id) {
        this.id = id;
    }

    public int getKmsTa() {
        return kmsTa;
    }

    public void setKmsTa(int kmsTa) {
        this.kmsTa = kmsTa;
    }

    public int getDureetr() {
        return dureetr;
    }

    public void setDureetr(int dureetr) {
        this.dureetr = dureetr;
    }

    public int getEscale() {
        return escale;
    }

    public void setEscale(int escale) {
        this.escale = escale;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
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

    public DrItin(DrItinId id, int kmsTa, int dureetr, int escale, String dates, int tarif, DrStati station) {
        this.id = id;
        this.kmsTa = kmsTa;
        this.dureetr = dureetr;
        this.escale = escale;
        this.dates = dates;
        this.tarif = tarif;
        this.station = station;
    }

    
}