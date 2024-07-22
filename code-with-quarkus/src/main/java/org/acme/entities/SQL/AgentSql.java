package org.acme.entities.SQL;



public class AgentSql {
    public Long decagen;

    public String denagea;

    public String denagen;

    public int decdeleg;

    public Long getDecagen() {
        return decagen;
    }

    public void setDecagen(Long decagen) {
        this.decagen = decagen;
    }

    public String getDenagea() {
        return denagea;
    }

    public void setDenagea(String denagea) {
        this.denagea = denagea;
    }

    public String getDenagen() {
        return denagen;
    }

    public void setDenagen(String denagen) {
        this.denagen = denagen;
    }

    public int getDecdeleg() {
        return decdeleg;
    }

    public void setDecdeleg(int decdeleg) {
        this.decdeleg = decdeleg;
    }

    public AgentSql(Long decagen, String denagea, String denagen, int decdeleg) {
        this.decagen = decagen;
        this.denagea = denagea;
        this.denagen = denagen;
        this.decdeleg = decdeleg;
    }

    public AgentSql() {
    }


}
