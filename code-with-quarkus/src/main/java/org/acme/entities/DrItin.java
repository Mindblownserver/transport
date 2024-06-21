package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DrItin {
    @Id
    @Column(name="DENUMLI")
    private Long numLigne;
    // DENUMLI,DENUMLG,DECSTAT,DEKMSTA,DEDURTR,DEESCALE,DEDATES,DETARIF
}
