package org.acme.entities.SQL;

import org.acme.entities.SQL.DrStatiSql;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.entities.DrLigne;
public class DrItinSql extends PanacheEntityBase{
    private Long deNumLi;
    private Long deNumLg;
    private Integer deKmsTa;
    private Integer deDureeTr;
    private Integer deEscale;
    private String deDates; // peut ne pas être avec les horraires
    private Integer deTarif;
    private long station;
    private DrLigne ligne;

    public Long getDeNumLi() {
        return deNumLi;
    }

    public void setDeNumLi(Long deNumLi) {
        this.deNumLi = deNumLi;
    }

    public Long getDeNumLg() {
        return deNumLg;
    }

    public void setDeNumLg(Long deNumLg) {
        this.deNumLg = deNumLg;
    }

    public Integer getDeKmsTa() {
        return deKmsTa;
    }

    public void setDeKmsTa(Integer deKmsTa) {
        this.deKmsTa = deKmsTa;
    }

    public Integer getDeDureeTr() {
        return deDureeTr;
    }

    public void setDeDureeTr(Integer deDureeTr) {
        this.deDureeTr = deDureeTr;
    }

    public Integer getDeEscale() {
        return deEscale;
    }

    public void setDeEscale(Integer deEscale) {
        this.deEscale = deEscale;
    }

    public String getDeDates() {
        return deDates;
    }

    public void setDeDates(String deDates) {
        this.deDates = deDates;
    }

    public Integer getDeTarif() {
        return deTarif;
    }

    public void setDeTarif(Integer deTarif) {
        this.deTarif = deTarif;
    }

    public long getStation() {
        return station;
    }

    public void setStation(long station) {
        this.station = station;
    }

    public DrLigne getLigne() {
        return ligne;
    }

    public void setLigne(DrLigne ligne) {
        this.ligne = ligne;
    }

    public DrItinSql() {
    }

    public DrItinSql(Long deNumLi, Long deNumLg, Integer deKmsTa, Integer deDureeTr, Integer deEscale, String deDates, Integer deTarif, long station, DrLigne ligne) {
        this.deNumLi = deNumLi;
        this.deNumLg = deNumLg;
        this.deKmsTa = deKmsTa;
        this.deDureeTr = deDureeTr;
        this.deEscale = deEscale;
        this.deDates = deDates;
        this.deTarif = deTarif;
        this.station = station;
        this.ligne = ligne;
    }

}