package org.acme.entities;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Drligne{
    @Id
    @Column(name="DENUMLI")
    private Long dec_ligne;
    @Column(name="DENOMLI")
    private String NOM_ligne;
    @Column(name="DECTYLI")
    private Integer type_ligne;
    @Column(name="DEPRIOR")
    private Long priorite_ligne;
    @Column(name="DECTYTA")
    private String DECTYTA;
    @Column(name="DECTYEQ")
    private Integer DECTYEQ;
    @Column(name="DECCENT")
    private Long DECCENT;
    @Column(name="DENOMLA")
    private String DENOMLA;
    @Column(name="DEDATEC")
    private Date DETATEC;
    @Column(name="DEDATEA")
    private Date DETATEA;
    @Column(name="DESTATU")
    private String DESTATU;
    @Column(name="DENBRKM")
    private Integer DENBRKM;
    @Column(name="DECDELEG")
    private Long DECDELEG;
    @Column(name="DEACTIF")
    private Integer DEACTIF;
    @Column(name="AGENCY_ID")
    private String AGENCY_ID;
    @Column(name="ROUTE_TYPE")
    private Long ROUTE_TYPE;
    @Column(name="ROUTE_URL")
    private String ROUTE_URL;
    @Column(name="ROUTE_COLOR")
    private Integer ROUTE_COLOR;
    @Column(name="ROUTE_TEXT_COLOR")
    private Long ROUTE_TEXT_COLOR;
    @Column(name="DENOMLI_RET")
    private String DENOMLI_RET;
    @Column(name="INTEG_BI")
    private Integer INTEG_BI;
    @Column(name="DECCLIE")
    private Long DECCLIE;
    @Column(name="DECADMI")
    private Integer DECADMI;
    @Column(name="SAE")
    private Integer SAE;
    

    
    public Long getDec_ligne() {
        return dec_ligne;
    }
    public void setDec_ligne(Long dec_ligne) {
        this.dec_ligne = dec_ligne;
    }
    public String getNOM_ligne() {
        return NOM_ligne;
    }
    public void setNOM_ligne(String nOM_ligne) {
        NOM_ligne = nOM_ligne;
    }
    public Integer getType_ligne() {
        return type_ligne;
    }
    public void setType_ligne(Integer type_ligne) {
        this.type_ligne = type_ligne;
    }
    public Long getPriorite_ligne() {
        return priorite_ligne;
    }
    public void setPriorite_ligne(Long priorite_ligne) {
        this.priorite_ligne = priorite_ligne;
    }
    public String getDECTYTA() {
        return DECTYTA;
    }
    public void setDECTYTA(String dECTYTA) {
        DECTYTA = dECTYTA;
    }
    public Integer getDECTYEQ() {
        return DECTYEQ;
    }
    public void setDECTYEQ(Integer dECTYEQ) {
        DECTYEQ = dECTYEQ;
    }
    public Long getDECCENT() {
        return DECCENT;
    }
    public void setDECCENT(Long dECCENT) {
        DECCENT = dECCENT;
    }
    public String getDENOMLA() {
        return DENOMLA;
    }
    public void setDENOMLA(String dENOMLA) {
        DENOMLA = dENOMLA;
    }
    public Date getDETATEC() {
        return DETATEC;
    }
    public void setDETATEC(Date dETATEC) {
        DETATEC = dETATEC;
    }
    public Date getDETATEA() {
        return DETATEA;
    }
    public void setDETATEA(Date dETATEA) {
        DETATEA = dETATEA;
    }
    public String getDESTATU() {
        return DESTATU;
    }
    public void setDESTATU(String dESTATU) {
        DESTATU = dESTATU;
    }
    public Integer getDENBRKM() {
        return DENBRKM;
    }
    public void setDENBRKM(Integer dENBRKM) {
        DENBRKM = dENBRKM;
    }
    public Long getDECDELEG() {
        return DECDELEG;
    }
    public void setDECDELEG(Long dECDELEG) {
        DECDELEG = dECDELEG;
    }
    public Integer getDEACTIF() {
        return DEACTIF;
    }
    public void setDEACTIF(Integer dEACTIF) {
        DEACTIF = dEACTIF;
    }
    public String getAGENCY_ID() {
        return AGENCY_ID;
    }
    public void setAGENCY_ID(String aGENCY_ID) {
        AGENCY_ID = aGENCY_ID;
    }
    public Long getROUTE_TYPE() {
        return ROUTE_TYPE;
    }
    public void setROUTE_TYPE(Long rOUTE_TYPE) {
        ROUTE_TYPE = rOUTE_TYPE;
    }
    public String getROUTE_URL() {
        return ROUTE_URL;
    }
    public void setROUTE_URL(String rOUTE_URL) {
        ROUTE_URL = rOUTE_URL;
    }
    public Integer getROUTE_COLOR() {
        return ROUTE_COLOR;
    }
    public void setROUTE_COLOR(Integer rOUTE_COLOR) {
        ROUTE_COLOR = rOUTE_COLOR;
    }
    public Long getROUTE_TEXT_COLOR() {
        return ROUTE_TEXT_COLOR;
    }
    public void setROUTE_TEXT_COLOR(Long rOUTE_TEXT_COLOR) {
        ROUTE_TEXT_COLOR = rOUTE_TEXT_COLOR;
    }
    public String getDENOMLI_RET() {
        return DENOMLI_RET;
    }
    public void setDENOMLI_RET(String dENOMLI_RET) {
        DENOMLI_RET = dENOMLI_RET;
    }
    public Integer getINTEG_BI() {
        return INTEG_BI;
    }
    public void setINTEG_BI(Integer iNTEG_BI) {
        INTEG_BI = iNTEG_BI;
    }
    public Long getDECCLIE() {
        return DECCLIE;
    }
    public void setDECCLIE(Long dECCLIE) {
        DECCLIE = dECCLIE;
    }
    public Integer getDECADMI() {
        return DECADMI;
    }
    public void setDECADMI(Integer dECADMI) {
        DECADMI = dECADMI;
    }
    public Integer getSAE() {
        return SAE;
    }
    public void setSAE(Integer sAE) {
        SAE = sAE;
    }
    public Drligne() {
    }
    public Drligne(Long dec_ligne, String nOM_ligne, Integer type_ligne, Long priorite_ligne, String dECTYTA,
            Integer dECTYEQ, Long dECCENT, String dENOMLA, Date dETATEC, Date dETATEA, String dESTATU, Integer dENBRKM,
            Long dECDELEG, Integer dEACTIF, String aGENCY_ID, Long rOUTE_TYPE, String rOUTE_URL, Integer rOUTE_COLOR,
            Long rOUTE_TEXT_COLOR, String dENOMLI_RET, Integer iNTEG_BI, Long dECCLIE, Integer dECADMI, Integer sAE) {
        this.dec_ligne = dec_ligne;
        NOM_ligne = nOM_ligne;
        this.type_ligne = type_ligne;
        this.priorite_ligne = priorite_ligne;
        DECTYTA = dECTYTA;
        DECTYEQ = dECTYEQ;
        DECCENT = dECCENT;
        DENOMLA = dENOMLA;
        DETATEC = dETATEC;
        DETATEA = dETATEA;
        DESTATU = dESTATU;
        DENBRKM = dENBRKM;
        DECDELEG = dECDELEG;
        DEACTIF = dEACTIF;
        AGENCY_ID = aGENCY_ID;
        ROUTE_TYPE = rOUTE_TYPE;
        ROUTE_URL = rOUTE_URL;
        ROUTE_COLOR = rOUTE_COLOR;
        ROUTE_TEXT_COLOR = rOUTE_TEXT_COLOR;
        DENOMLI_RET = dENOMLI_RET;
        INTEG_BI = iNTEG_BI;
        DECCLIE = dECCLIE;
        DECADMI = dECADMI;
        SAE = sAE;
    }
    

    
}