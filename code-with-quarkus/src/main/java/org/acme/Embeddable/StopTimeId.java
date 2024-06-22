package org.acme.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public record StopTimeId (
        @Column(name="DEDATED") Date deDated,
        @Column(name="TRIP_ID") Integer tripId,
        @Column(name="STOP_SEQUENCE") Integer stopSequence) {
}
