
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
    private Integer deKmsTa;

    @Column(name="DEDURTR")
    private Integer deDureeTr;

    @Column(name="DEESCALE", nullable = true)
    private Integer deEscale;

    @Column(name="DEDATES")
    private String deDates; // peut ne pas être avec les horraires

    @Column(name="DETARIF")
    private Integer deTarif;

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
        return deKmsTa;
    }

    public void setKmsTa(Integer deKmsTa) {
        this.deKmsTa = deKmsTa;
    }

    public Integer getDureetr() {
        return deDureeTr;
    }

    public void setDureetr(Integer deDureeTr) {
        this.deDureeTr = deDureeTr;
    }

    public Integer getEscale() {
        return deEscale;
    }

    public void setEscale(Integer deEscale) {
        this.deEscale = deEscale;
    }

    public String getDates() {
        return deDates;
    }

    public void setDates(String deDates) {
        this.deDates = deDates;
    }

    public Integer getTarif() {
        return deTarif;
    }

    public void setTarif(Integer deTarif) {
        this.deTarif = deTarif;
    }

    public DrStati getStation() {
        return station;
    }

    public void setStation(DrStati station) {
        this.station = station;
    }

    public DrItin() {
    }

    public DrItin(DrItinIdRecord id, Integer deKmsTa, Integer deDureeTr, Integer deEscale, String deDates, Integer deTarif, DrStati station) {
        this.id = id;
        this.deKmsTa = deKmsTa;
        this.deDureeTr = deDureeTr;
        this.deEscale = deEscale;
        this.deDates = deDates;
        this.deTarif = deTarif;
        this.station = station;
    }


}
