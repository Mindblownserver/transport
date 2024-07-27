package org.acme.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record ShapsId(
    @Column(name="DENUMLI") Long deNumLi,
    @Column(name="N") Integer N,
    @Column(name="DIRECTION_ID") int DIRECTION_ID){

}
