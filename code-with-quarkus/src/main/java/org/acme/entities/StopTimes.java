package org.acme.entities;

import java.util.Date;

import org.acme.Embeddable.StopTimesId;


public class StopTimes{
    
    StopTimesId id;

    
    private Date arrival_time;
    
    
    private Date departure_time;
    
    
    private Long pickup_type;
    
    
    private String shape_dist_traveled;
    
    
    private Integer timepoint;
    
    
    private Date rt_arrival_time;
    
    
    private Date rt_departure_time;
    
    
    private String sate;    
    
    
    private DrStati station;

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


    public DrStati getStation() {
        return station;
    }


    public void setDecstat(DrStati station) {
        this.station = station;
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
            DrStati station, Long pickup_type, String shape_dist_traveled, Integer timepoint, Date rt_arrival_time,
            Date rt_departure_time, String sate) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.station = station;
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