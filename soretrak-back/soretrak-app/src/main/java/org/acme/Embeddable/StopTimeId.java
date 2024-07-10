package org.acme.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Date;

@Embeddable
public record StopTimeId (
        @Embedded TripsId tripsDatedId,
        @Column(name="STOP_SEQUENCE") Integer stopSequence) {
}
