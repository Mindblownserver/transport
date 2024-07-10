package org.acme.Embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public record TripsId(
    Date deDated,
    @Column(name="TRIP_ID") Integer tripId){
}
