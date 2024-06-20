package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class DrLigne {
    @Id
    @Column(name="DENUMLI")
    private Integer numli;
    @Column(name="DENOMLI")
    private String nomli;
    @Column(name="DECTYLI")
    private Integer tyli;
    @Column(name="DEPRIOR")
    private Integer prior;
    @Column(name="DECTYTA")
    private String tyta;
    @Column(name="DECTYEQ")
    private Integer tyeq;
    @Column(name="DECCENT")
    private Integer cent;
    @Column(name="DENOMLA")
    private String nomla;
    @Column(name="DEDATEC")
    private Date datec;
    @Column(name="DEDATEA")
    private Date datea;
    @Column(name="DESTATU")
    private String statu;
    @Column(name="DENBRKM")
    private Double nbrkm;
    @Column(name="DECDELEG")
    private Integer deleg;
    @Column(name="DEACTIF")
    private Integer actif;
    @Column(name="AGENCY_ID")
    private String agencyId;
    @Column(name="ROUTE_TYPE")
    private Integer routeType;
    @Column(name="ROUTE_URL")
    private String routeUrl;
    @Column(name="ROUTE_COLOR")
    private Integer routeColor;
    @Column(name="ROUTE_TEXT_COLOR")
    private String routeTextColor;
    @Column(name="DENOMLI_RET")
    private String denomliRet;
    @Column(name="INTEG_BI")
    private Integer integBi;
    @Column(name="DECCLIE")
    private Integer clie;
    @Column(name="DECADMI")
    private Integer admi;
    @Column(name="SAE")
    private Integer sae;

    public Integer getNumli() {
        return numli;
    }

    public void setNumli(Integer numli) {
        this.numli = numli;
    }

    public String getNomli() {
        return nomli;
    }

    public void setNomli(String nomli) {
        this.nomli = nomli;
    }

    public Integer getTyli() {
        return tyli;
    }

    public void setTyli(Integer tyli) {
        this.tyli = tyli;
    }

    public Integer getPrior() {
        return prior;
    }

    public void setPrior(Integer prior) {
        this.prior = prior;
    }

    public String getTyta() {
        return tyta;
    }

    public void setTyta(String tyta) {
        this.tyta = tyta;
    }

    public Integer getTyeq() {
        return tyeq;
    }

    public void setTyeq(Integer tyeq) {
        this.tyeq = tyeq;
    }

    public Integer getCent() {
        return cent;
    }

    public void setCent(Integer cent) {
        this.cent = cent;
    }

    public String getNomla() {
        return nomla;
    }

    public void setNomla(String nomla) {
        this.nomla = nomla;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public Date getDatea() {
        return datea;
    }

    public void setDatea(Date datea) {
        this.datea = datea;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public Double getNbrkm() {
        return nbrkm;
    }

    public void setNbrkm(Double nbrkm) {
        this.nbrkm = nbrkm;
    }

    public Integer getDeleg() {
        return deleg;
    }

    public void setDeleg(Integer deleg) {
        this.deleg = deleg;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public Integer getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(Integer routeColor) {
        this.routeColor = routeColor;
    }

    public String getRouteTextColor() {
        return routeTextColor;
    }

    public void setRouteTextColor(String routeTextColor) {
        this.routeTextColor = routeTextColor;
    }

    public String getDenomliRet() {
        return denomliRet;
    }

    public void setDenomliRet(String denomliRet) {
        this.denomliRet = denomliRet;
    }

    public Integer getIntegBi() {
        return integBi;
    }

    public void setIntegBi(Integer integBi) {
        this.integBi = integBi;
    }

    public Integer getClie() {
        return clie;
    }

    public void setClie(Integer clie) {
        this.clie = clie;
    }

    public Integer getAdmi() {
        return admi;
    }

    public void setAdmi(Integer admi) {
        this.admi = admi;
    }

    public Integer getSae() {
        return sae;
    }

    public void setSae(Integer sae) {
        this.sae = sae;
    }

    public DrLigne(Integer numli, String nomli, Integer tyli, Integer prior, String tyta, Integer tyeq, Integer cent, String nomla, Date datec, Date datea, String statu, Double nbrkm, Integer deleg, Integer actif, String agencyId, Integer routeType, String routeUrl, Integer routeColor, String routeTextColor, String denomliRet, Integer integBi, Integer clie, Integer admi, Integer sae) {
        this.numli = numli;
        this.nomli = nomli;
        this.tyli = tyli;
        this.prior = prior;
        this.tyta = tyta;
        this.tyeq = tyeq;
        this.cent = cent;
        this.nomla = nomla;
        this.datec = datec;
        this.datea = datea;
        this.statu = statu;
        this.nbrkm = nbrkm;
        this.deleg = deleg;
        this.actif = actif;
        this.agencyId = agencyId;
        this.routeType = routeType;
        this.routeUrl = routeUrl;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
        this.denomliRet = denomliRet;
        this.integBi = integBi;
        this.clie = clie;
        this.admi = admi;
        this.sae = sae;
    }
    public DrLigne(){}
}
