package org.acme.repositories.SQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.acme.Embeddable.StopTimesId;
import org.acme.Embeddable.TripsId;
import org.acme.entities.StopTimes;
import org.acme.entities.SQL.TripsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TripsSqlRepository {
    @Inject
    DataSource dataSource;
    public List<StopTimes> getStopTimesByTripsId(Date dedated, int trip_id)throws SQLException{
        List<StopTimes> stopTimesList = new ArrayList<>();
        TripsId tipsId = new TripsId(dedated, trip_id);
        Connection conn = dataSource.getConnection();
        String sql = "SELECT * FROM STOP_TIMES where trip_id=? and dedated = ? order by stop_sequence desc";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, trip_id);
        ps.setDate(2,new java.sql.Date(dedated.getTime()));
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            stopTimesList.add(new StopTimes(new StopTimesId(tipsId, rs.getInt("STOP_SEQUENCE")),rs.getTimestamp("ARRIVAL_TIME"), 
            rs.getTimestamp("DEPARTURE_TIME"), rs.getInt("DECSTAT"), 
            rs.getLong("PICKUP_TYPE"), rs.getString("SHAPE_DIST_TRAVELED"), 
            rs.getInt("TIMEPOINT"), rs.getTimestamp("RT_ARRIVAL_TIME"),
            rs.getTimestamp("RT_DEPARTURE_TIME"), rs.getString("STATE")));
        }
        ps.close();
        conn.close();
        return stopTimesList;
    }


    public List<TripsSql> getTripsByDate(Date date)throws SQLException{
        List<TripsSql> tripsList=  new ArrayList<>();

        Connection conn = dataSource.getConnection();
        
        String sql = "SELECT DEDATED,TRIP_ID,DENUMLI,SERVICE_ID,DIRECTION_ID,TIME_DEPART,HAVERET,TIME_NRET,TRIP_NID,GRP,BUS_PR,BUS_RE,CHAUFF_PR,CHAUFF_RE,REC_PR,REC_RE,ETAT,"+
        "TIME_DEPART_R,TIME_ARRIVE_R,VMAX,AVANCE_RETARD,CHANGEMENT,META_DATA,DEVALID,ALERT FROM TRIPS where DEDATED = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            TripsSql trip = new TripsSql(
            new TripsId(rs.getDate("DEDATED"), rs.getInt("TRIP_ID")),
            rs.getInt("SERVICE_ID"),
            rs.getInt("DIRECTION_ID"),
            rs.getTimestamp("TIME_DEPART"),
            rs.getInt("HAVERET"),
            rs.getString("TIME_NRET"),
            rs.getInt("TRIP_NID"),
            rs.getInt("GRP"),
            rs.getInt("BUS_RE"),
            rs.getInt("CHAUFF_PR"),
            rs.getInt("CHAUFF_RE"),
            rs.getInt("REC_PR"),
            rs.getInt("REC_RE"),
            rs.getInt("ETAT"),
            rs.getDate("TIME_DEPART_R"),
            rs.getDate("TIME_ARRIVE_R"),
            rs.getInt("VMAX"),
            rs.getInt("AVANCE_RETARD"),
            rs.getInt("CHANGEMENT"),
            rs.getString("META_DATA"),
            rs.getInt("DEVALID"),
            rs.getInt("ALERT")
            );
            List<StopTimes> stopTimesList = getStopTimesByTripsId(rs.getDate("DEDATED"), rs.getInt("TRIP_ID"));
            trip.setStopTimesList(stopTimesList);
            tripsList.add(trip);
        }
        ps.close();
        conn.close();
        return tripsList;
    }
}
