package org.acme.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record DrItinIdRecord(
    @Column(name="DENUMLI")Long deNumLi, 
    @Column(name="DENUMLG") Long deNumLg) {

}
