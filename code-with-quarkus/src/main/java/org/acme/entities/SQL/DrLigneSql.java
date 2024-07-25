package org.acme.entities.SQL;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import org.acme.entities.*;

public class DrLigneSql {
    private Long idLigne;
    private String nomLigne;
    private Long priorite;
    private String tyTa;
    private Integer tyEq;
    private String nomLa;
    private Date taTec;
    private Date taTea;
    private String statu;
    private Integer nbrkm;
    private Integer actif;
    private String agencyId;
    private Long routeType;
    private String routeUrl;
    private Integer routeColor;
    private Long routeTextColor;
    private String nomLiRet;
    private Integer integBi;
    private Long clie;
    private Integer admi;
    private Integer sae;
    private DrTypeLigne type;
    private DrCentre centre;
    private DrDeleg deleg;
    private List<DrItinSql> itinList;
    private List<SHAPS> shapList;
    private List<Trips> tripsList;
    
    public Long getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(Long idLigne) {
        this.idLigne = idLigne;
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

    public Long getPriorite() {
        return priorite;
    }

    public void setPriorite(Long priorite) {
        this.priorite = priorite;
    }

    public String getTyTa() {
        return tyTa;
    }

    public void setTyTa(String tyTa) {
        this.tyTa = tyTa;
    }

    public Integer getTyEq() {
        return tyEq;
    }

    public void setTyEq(Integer tyEq) {
        this.tyEq = tyEq;
    }

    public String getNomLa() {
        return nomLa;
    }

    public void setNomLa(String nomLa) {
        this.nomLa = nomLa;
    }

    public Date getTaTec() {
        return taTec;
    }

    public void setTaTec(Date taTec) {
        this.taTec = taTec;
    }

    public Date getTaTea() {
        return taTea;
    }

    public void setTaTea(Date taTea) {
        this.taTea = taTea;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public Integer getNbrkm() {
        return nbrkm;
    }

    public void setNbrkm(Integer nbrkm) {
        this.nbrkm = nbrkm;
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

    public Long getRouteType() {
        return routeType;
    }

    public void setRouteType(Long routeType) {
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

    public Long getRouteTextColor() {
        return routeTextColor;
    }

    public void setRouteTextColor(Long routeTextColor) {
        this.routeTextColor = routeTextColor;
    }

    public String getNomLiRet() {
        return nomLiRet;
    }

    public void setNomLiRet(String nomLiRet) {
        this.nomLiRet = nomLiRet;
    }

    public Integer getIntegBi() {
        return integBi;
    }

    public void setIntegBi(Integer integBi) {
        this.integBi = integBi;
    }

    public Long getClie() {
        return clie;
    }

    public void setClie(Long clie) {
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

    public DrTypeLigne getType() {
        return type;
    }

    public void setType(DrTypeLigne type) {
        this.type = type;
    }

    public DrCentre getCentre() {
        return centre;
    }

    public void setCentre(DrCentre centre) {
        this.centre = centre;
    }

    public DrDeleg getDeleg() {
        return deleg;
    }

    public void setDeleg(DrDeleg deleg) {
        this.deleg = deleg;
    }

    public List<Trips> getTripsList() {
        return tripsList;
    }

    public void setTripsList(List<Trips> tripsList) {
        this.tripsList = tripsList;
    }

    public List<DrItinSql> getItinList() {
        return itinList;
    }

    public void setItinList(List<DrItinSql> itinList) {
        this.itinList = itinList;
    }

    public List<SHAPS> getShapList() {
        return shapList;
    }

    public void setShapList(List<SHAPS> shapList) {
        this.shapList = shapList;
    }

    public DrLigneSql() {
    }

    public DrLigneSql(Long idLigne, String nomLigne, Long priorite, String tyTa, Integer tyEq, String nomLa, Date taTec, Date taTea, String statu, Integer nbrkm, Integer actif, String agencyId, Long routeType, String routeUrl, Integer routeColor, Long routeTextColor, String nomLiRet, Integer integBi, Long clie, Integer admi, Integer sae) {
        this.idLigne = idLigne;
        this.nomLigne = nomLigne;
        this.priorite = priorite;
        this.tyTa = tyTa;
        this.tyEq = tyEq;
        this.nomLa = nomLa;
        this.taTec = taTec;
        this.taTea = taTea;
        this.statu = statu;
        this.nbrkm = nbrkm;
        this.actif = actif;
        this.agencyId = agencyId;
        this.routeType = routeType;
        this.routeUrl = routeUrl;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
        this.nomLiRet = nomLiRet;
        this.integBi = integBi;
        this.clie = clie;
        this.admi = admi;
        this.sae = sae;
    }
}
