package org.acme.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record DrItinIdRecord(
    @Column(name="DENUMLI")Long numLigne, 
    @Column(name="DENUMLG") Long numLg) {

}
