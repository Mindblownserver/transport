package org.acme.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DrItinId implements Serializable{
    @Column(name="DENUMLI")
    private Long numLigne;
    
    @Column(name="DENUMLG")
    private Long numLg;
    
    public Long getNumLigne() {
        return numLigne;
    }
    
    public void setNumLigne(Long numLigne) {
        this.numLigne = numLigne;
    }
    
    public Long getNumLg() {
        return numLg;
    }
    
    public void setNumLg(Long numLg) {
        this.numLg = numLg;
    }
    
    public DrItinId(Long numLigne, Long numLg) {
        this.numLigne = numLigne;
        this.numLg = numLg;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numLigne == null) ? 0 : numLigne.hashCode());
        result = prime * result + ((numLg == null) ? 0 : numLg.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DrItinId other = (DrItinId) obj;
        if (numLigne == null) {
            if (other.numLigne != null)
                return false;
        } else if (!numLigne.equals(other.numLigne))
            return false;
        if (numLg == null) {
            if (other.numLg != null)
                return false;
        } else if (!numLg.equals(other.numLg))
            return false;
        return true;
    }

    public DrItinId() {
    }
     
}
