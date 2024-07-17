package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "DrAGENT")
public class Agent {
    @Id
    @Column(name = "DECAGEN", nullable = false)
    public Long decagen;

    @Column(name = "DENAGEA", length = 50)
    public String denagea;

    @Column(name = "DENAGEN", length = 50)
    public String denagen;

    @Column(name = "DECDELEG")
    public int decdeleg;

    @Transient
    public String color ="#18851a";

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

    public Agent() {
    }

    public String getColor() {
        return color;
    }

    
}
