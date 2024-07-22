package org.acme.entities;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Bus extends PanacheEntityBase{
    @Id
    private Long bus_id;

    private String bus_type;

    private String color;

    @OneToMany(mappedBy = "busPr") // name of the attribute, not the column
    private List<Trips> tripsList;

    public Bus(Long bus_id, String bus_type) {
        this.bus_id = bus_id;
        this.bus_type = bus_type;
    }

    public Bus(){

    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
