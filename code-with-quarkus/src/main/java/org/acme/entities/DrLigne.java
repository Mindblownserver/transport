package org.acme.entities;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;


@Entity
public class DrLigne{
    @Id
    @Column(name="DENUMLI")
    private Long deNumLi;

    @Column(name="DENOMLI")
    private String deNomLi;

    @Column(name="DEPRIOR")
    private Long dePrior;

    @Column(name="DECTYTA")
    private String decTyTa;

    @Column(name="DECTYEQ")
    private Integer decTyEq;

    @Column(name="DENOMLA")
    private String deNomLa;

    @Column(name="DEDATEC")
    private Date deTaTec;

    @Column(name="DEDATEA")
    private Date deTaTea;

    @Column(name="DESTATU")
    private String deStatu;

    @Column(name="DENBRKM")
    private Integer deNbrkm;

    @Column(name="DECDELEG")
    private Long decDeleg;

    @Column(name="DEACTIF")
    private Integer deActif;

    @Column(name="AGENCY_ID")
    private String agencyId;

    @Column(name="ROUTE_TYPE")
    private Long routeType;

    @Column(name="ROUTE_URL")
    private String routeUrl;

    @Column(name="ROUTE_COLOR")
    private Integer routeColor;

    @Column(name="ROUTE_TEXT_COLOR")
    private Long routeTextColor;

    @Column(name="DENOMLI_RET")
    private String deNomLiRet;

    @Column(name="INTEG_BI")
    private Integer integBi;

    @Column(name="DECCLIE")
    private Long decClie;

    @Column(name="DECADMI")
    private Integer decAdmi;

    @Column(name="SAE")
    private Integer sae;

    @ManyToOne
    @JoinColumn(name="DECTYLI")
    private DrTypeLigne type;

    @ManyToOne
    @JoinColumn(name="DECCENT")
    private DrCentre centre;

    @OneToMany(mappedBy = "ligne", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SHAPS> shapList;

    @OneToMany(mappedBy = "ligne", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Trips> tripsList;

    public Long getIdLigne() {
        return deNumLi;
    }

    public void setIdLigne(Long deNumLi) {
        this.deNumLi = deNumLi;
    }

    public String getNomLigne() {
        return deNomLi;
    }

    public void setNomLigne(String deNomLi) {
        this.deNomLi = deNomLi;
    }

    public Long getPriorite() {
        return dePrior;
    }

    public void setPriorite(Long dePrior) {
        this.dePrior = dePrior;
    }

    public String getDECTYTA() {
        return decTyTa;
    }

    public void setDECTYTA(String decTyTa) {
        this.decTyTa = decTyTa;
    }

    public Integer getDECTYEQ() {
        return decTyEq;
    }

    public void setDECTYEQ(Integer decTyEq) {
        this.decTyEq = decTyEq;
    }

    public String getDENOMLA() {
        return deNomLa;
    }

    public void setDENOMLA(String deNomLa) {
        this.deNomLa = deNomLa;
    }

    public Date getDETATEC() {
        return deTaTec;
    }

    public void setDETATEC(Date deTaTec) {
        this.deTaTec = deTaTec;
    }

    public Date getDETATEA() {
        return deTaTea;
    }

    public void setDETATEA(Date deTaTea) {
        this.deTaTea = deTaTea;
    }

    public String getDESTATU() {
        return deStatu;
    }

    public void setDESTATU(String deStatu) {
        this.deStatu = deStatu;
    }

    public Integer getDENBRKM() {
        return deNbrkm;
    }

    public void setDENBRKM(Integer deNbrkm) {
        this.deNbrkm = deNbrkm;
    }

    public Long getDECDELEG() {
        return decDeleg;
    }

    public void setDECDELEG(Long decDeleg) {
        this.decDeleg = decDeleg;
    }

    public Integer getDEACTIF() {
        return deActif;
    }

    public void setDEACTIF(Integer deActif) {
        this.deActif = deActif;
    }

    public String getAGENCY_ID() {
        return agencyId;
    }

    public void setAGENCY_ID(String agencyId) {
        this.agencyId = agencyId;
    }

    public Long getROUTE_TYPE() {
        return routeType;
    }

    public void setROUTE_TYPE(Long routeType) {
        this.routeType = routeType;
    }

    public String getROUTE_URL() {
        return routeUrl;
    }

    public void setROUTE_URL(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public Integer getROUTE_COLOR() {
        return routeColor;
    }

    public void setROUTE_COLOR(Integer routeColor) {
        this.routeColor = routeColor;
    }

    public Long getROUTE_TEXT_COLOR() {
        return routeTextColor;
    }

    public void setROUTE_TEXT_COLOR(Long routeTextColor) {
        this.routeTextColor = routeTextColor;
    }

    public String getDENOMLI_RET() {
        return deNomLiRet;
    }

    public void setDENOMLI_RET(String deNomLiRet) {
        this.deNomLiRet = deNomLiRet;
    }

    public Integer getINTEG_BI() {
        return integBi;
    }

    public void setINTEG_BI(Integer integBi) {
        this.integBi = integBi;
    }

    public Long getDECCLIE() {
        return decClie;
    }

    public void setDECCLIE(Long decClie) {
        this.decClie = decClie;
    }

    public Integer getDECADMI() {
        return decAdmi;
    }

    public void setDECADMI(Integer decAdmi) {
        this.decAdmi = decAdmi;
    }

    public Integer getSAE() {
        return sae;
    }

    public void setSAE(Integer sae) {
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

    public DrLigne() {
    }

    public DrLigne(Long deNumLi, String deNomLi, Long dePrior, String decTyTa, Integer decTyEq, String deNomLa,
                   Date deTaTec, Date deTaTea, String deStatu, Integer deNbrkm, Long decDeleg, Integer deActif,
                   String agencyId, Long routeType, String routeUrl, Integer routeColor, Long routeTextColor,
                   String deNomLiRet, Integer integBi, Long decClie, Integer decAdmi, Integer sae, DrTypeLigne type,
                   DrCentre centre) {
        this.deNumLi = deNumLi;
        this.deNomLi = deNomLi;
        this.dePrior = dePrior;
        this.decTyTa = decTyTa;
        this.decTyEq = decTyEq;
        this.deNomLa = deNomLa;
        this.deTaTec = deTaTec;
        this.deTaTea = deTaTea;
        this.deStatu = deStatu;
        this.deNbrkm = deNbrkm;
        this.decDeleg = decDeleg;
        this.deActif = deActif;
        this.agencyId = agencyId;
        this.routeType = routeType;
        this.routeUrl = routeUrl;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
        this.deNomLiRet = deNomLiRet;
        this.integBi = integBi;
        this.decClie = decClie;
        this.decAdmi = decAdmi;
        this.sae = sae;
        this.type = type;
        this.centre = centre;
    }


}