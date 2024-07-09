package org.acme.Embeddable;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record StopTimesId(
    /* @Column Date dedated,
    @Column int tripsId, */
    @Embedded TripsId tripsDatedId,
    @Column(name="STOP_SEQUENCE") int stopSeq
) implements Serializable{

}
