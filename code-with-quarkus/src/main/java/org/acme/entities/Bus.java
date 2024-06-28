package org.acme.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Bus {
    @Id
    private Long bus_id;

    private String bus_type;

    
    public Long getBus_id() {
        return bus_id;
    }

    public void setBus_id(Long bus_id) {
        this.bus_id = bus_id;
    }

    public String getBus_type() {
        return bus_type;
    }

    public void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    @OneToMany(mappedBy = "busPr")
    private List<Trips> tripsList;

    public Bus(Long bus_id, String bus_type) {
        this.bus_id = bus_id;
        this.bus_type = bus_type;
    }

    public Bus(){

    }
}
