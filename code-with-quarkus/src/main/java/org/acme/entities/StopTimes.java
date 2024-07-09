package org.acme.entities;

import java.util.Date;

import org.acme.Embeddable.StopTimesId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="stop_times")
public class StopTimes{
    @EmbeddedId 
    StopTimesId id;

    @Column(name="ARRIVAL_TIME")
    private Date arrival_time;
    
    @Column(name="DEPARTURE_TIME")
    private Date departure_time;
    
    @Column(name="PICKUP_TYPE")
    private Long pickup_type;
    
    @Column(name="SHAPE_DIST_TRAVELED")
    private String shape_dist_traveled;
    
    @Column(name="TIMEPOINT")
    private Integer timepoint;
    
    @Column(name="RT_ARRIVAL_TIME")
    private Date rt_arrival_time;
    
    @Column(name="RT_DEPARTURE_TIME")
    private Date rt_departure_time;
    
    @Column(name="STATE")
    private String sate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="DEDATED",referencedColumnName = "deDated"),
        @JoinColumn(name="trip_ID",referencedColumnName = "trip_Id")
    })
    //@JsonBackReference
    private Trips trip;
    
    //@ManyToOne
    @Column(name="DECSTAT")
    private Integer decstat;
    
   /*  public Date getDedated() {
        return id.tripsDatedId().deDated();
    }


    public Integer getTrip_id() {
        return id.tripsDatedId().trip_id();
    } */
    
    public Integer getStop_sequence() {
        return id.stopSeq();
    }

    public Date getArrival_time() {
        return arrival_time;
    }


    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }


    public Date getDeparture_time() {
        return departure_time;
    }


    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
    }


    public Integer getDecstat() {
        return decstat;
    }


    public void setDecstat(Integer decstat) {
        this.decstat = decstat;
    }


    public Long getPickup_type() {
        return pickup_type;
    }


    public void setPickup_type(Long pickup_type) {
        this.pickup_type = pickup_type;
    }


    public String getShape_dist_traveled() {
        return shape_dist_traveled;
    }


    public void setShape_dist_traveled(String shape_dist_traveled) {
        this.shape_dist_traveled = shape_dist_traveled;
    }


    public Integer getTimepoint() {
        return timepoint;
    }


    public void setTimepoint(Integer timepoint) {
        this.timepoint = timepoint;
    }


    public Date getRt_arrival_time() {
        return rt_arrival_time;
    }


    public void setRt_arrival_time(Date rt_arrival_time) {
        this.rt_arrival_time = rt_arrival_time;
    }


    public Date getRt_departure_time() {
        return rt_departure_time;
    }


    public void setRt_departure_time(Date rt_departure_time) {
        this.rt_departure_time = rt_departure_time;
    }


    public String getSate() {
        return sate;
    }


    public void setSate(String sate) {
        this.sate = sate;
    }


    public StopTimes(StopTimesId id, Date arrival_time, Date departure_time,
            Integer decstat, Long pickup_type, String shape_dist_traveled, Integer timepoint, Date rt_arrival_time,
            Date rt_departure_time, String sate) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.decstat = decstat;
        this.pickup_type = pickup_type;
        this.shape_dist_traveled = shape_dist_traveled;
        this.timepoint = timepoint;
        this.rt_arrival_time = rt_arrival_time;
        this.rt_departure_time = rt_departure_time;
        this.sate = sate;
    }


    public StopTimes() {
    }

}