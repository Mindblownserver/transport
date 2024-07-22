package org.acme.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DRAGENT")
public class Agent {
    @Id
    @Column(name = "DECAGEN", nullable = false)
    public Long decagen;

    @Column(name = "DENAGEA", length = 50)
    public String denagea;

    @Column(name = "DENAGEN", length = 50)
    public String denagen;

    @ManyToOne
    @JoinColumn(name="DecDeleg")
    public DrDeleg decdeleg;

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

    public DrDeleg getDeleg() {
        return decdeleg;
    }

    public void setDeleg(DrDeleg decdeleg) {
        this.decdeleg = decdeleg;
    }

    public Agent() {
    }

    
}
