package org.acme.Embeddable;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record TripsId(
    Date deDated,
    @Column(name="TRIP_ID") Integer trip_id
)  implements Serializable{

}
