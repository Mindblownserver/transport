package org.acme.entities;
import java.util.Date;
import java.util.List;





public class DrLigne{
    
    
    private int deNumLi;
   
    
    private String deNomLi;
   
    
    private Long dePrior;
   
    
    private String decTyTa;

    
    private Integer decTyEq;

    
    private String deNomLa;
   
    
    private Date deTaTec;
   
    
    private Date deTaTea;
   
    
    private String deStatu;
   
    
    private Integer deNbrkm;
    
    
    private Integer deActif;
    
    
    private String agencyId;
    
    
    private Long routeType;
    
    
    private String routeUrl;
    
    
    private Integer routeColor;
    
    
    private Long routeTextColor;
    
    
    private String deNomLiRet;
    
    
    private Integer integBi;
    
    
    private Long decClie;
    
    
    private Integer decAdmi;
    
    
    private Integer sae;

    
    private String color = "#18851a";
    
    private DrTypeLigne type; 
    
    private DrCentre centre;
    
    private DrDeleg deleg;
    
    private List<Shapes> shapList;

    
    
    private List<DrItin> itinList;

    

    public int getIdLigne() {
        return deNumLi;
    }

    

    public List<Shapes> getShapList() {
        return shapList;
    }



    public void setShapList(List<Shapes> shapList) {
        this.shapList = shapList;
    }



    public List<DrItin> getItinList() {
        return itinList;
    }



    public void setItinList(List<DrItin> itinList) {
        this.itinList = itinList;
    }



    public void setIdLigne(int deNumLi) {
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


    public DrDeleg getDeleg() {
        return deleg;
    }

    public void setDeleg(DrDeleg deleg) {
        this.deleg = deleg;
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

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
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

    public DrLigne(int deNumLi, String deNomLi, Long dePrior, String decTyTa, Integer decTyEq, String deNomLa,
            Date deTaTec, Date deTaTea, String deStatu, Integer deNbrkm, DrDeleg decDeleg, Integer deActif,
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
        this.deleg = decDeleg;
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