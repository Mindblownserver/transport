package org.acme.repositories.SQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.acme.Embeddable.StopTimesId;
import org.acme.Embeddable.TripsId;
import org.acme.entities.Bus;
import org.acme.entities.DrLigne;
import org.acme.entities.DrStati;
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
    
    public DrLigne getLigneById(int denumli)throws SQLException{
        DrLigne ligne = new DrLigne();
        String sql = "select * from DrLigne where denumli = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1,denumli);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        ligne.setIdLigne((long)denumli);
                        ligne.setNomLigne(rs.getString("DENOMLI"));
                        ligne.setPriorite(rs.getLong("DEPRIOR"));
                        ligne.setDECTYTA(rs.getString("DECTYTA"));
                        ligne.setDECTYEQ(rs.getInt("DECTYEQ"));
                        ligne.setDENOMLA(rs.getString("DENOMLA"));
                        ligne.setDETATEC(rs.getDate("DEDATEC"));
                        ligne.setDETATEA(rs.getDate("DEDATEA"));
                        ligne.setDESTATU(rs.getString("DESTATU"));
                        ligne.setDENBRKM(rs.getInt("DENBRKM"));
                        ligne.setDEACTIF(rs.getInt("DEACTIF"));
                        ligne.setAGENCY_ID(rs.getString("AGENCY_ID"));
                        ligne.setROUTE_TYPE(rs.getLong("ROUTE_TYPE"));
                        ligne.setROUTE_URL(rs.getString("ROUTE_URL"));
                        ligne.setROUTE_COLOR(rs.getInt("ROUTE_COLOR"));
                        ligne.setROUTE_TEXT_COLOR(rs.getLong("ROUTE_TEXT_COLOR"));
                        ligne.setDENOMLI_RET(rs.getString("DENOMLI_RET"));
                        ligne.setINTEG_BI(rs.getInt("INTEG_BI"));
                        ligne.setDECCLIE(rs.getLong("DECCLIE"));
                        ligne.setDECADMI(rs.getInt("DECADMI"));
                        ligne.setSAE(rs.getInt("SAE"));
                    }
                    return ligne;
                }
            }
    }

    public DrStati getStationById(int decStat) throws SQLException{
        DrStati station = new DrStati();
        String sql = "SELECT * from DRSTATI where DECSTAT=?";
        
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, decStat);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    station.setDecStat((long)decStat);
                    station.setNomFr(rs.getString("DELSTAT"));
                    station.setNomAr(rs.getString("DELSTAA"));
                    station.setDate(rs.getTimestamp("DATE_SYS"));
                    station.setTypeSt(rs.getInt("DECTYST"));
                    station.setLatitude(rs.getFloat("STOP_LAT"));
                    station.setLongetude(rs.getFloat("STOP_LON"));
                    station.setRayon(rs.getInt("RAYON"));
                }
                return station;
            }
        }
    }
    
    public List<StopTimes> getStopTimesByTripsId(Date dedated, int trip_id)throws SQLException{
        List<StopTimes> stopTimesList = new ArrayList<>();
        TripsId tipsId = new TripsId(dedated, trip_id);
        Connection conn = dataSource.getConnection();
        String sql = "SELECT * FROM STOP_TIMES where trip_id=? and dedated = ? order by stop_sequence asc";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, trip_id);
        ps.setDate(2,new java.sql.Date(dedated.getTime()));
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            DrStati station = getStationById(rs.getInt("DECSTAT"));
            stopTimesList.add(new StopTimes(new StopTimesId(tipsId, rs.getInt("STOP_SEQUENCE")),rs.getTimestamp("ARRIVAL_TIME"), 
            rs.getTimestamp("DEPARTURE_TIME"), station, 
            rs.getLong("PICKUP_TYPE"), rs.getString("SHAPE_DIST_TRAVELED"), 
            rs.getInt("TIMEPOINT"), rs.getTimestamp("RT_ARRIVAL_TIME"),
            rs.getTimestamp("RT_DEPARTURE_TIME"), rs.getString("STATE")));
        }
        rs.close();
        ps.close();
        conn.close();
        return stopTimesList;
    }

    public Bus getBusById(int busId) throws SQLException{
        Bus bus= new Bus();
        Connection conn = dataSource.getConnection();
        String sql = "select * from BUS where bus_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,busId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            bus.setBus_id(rs.getLong("BUS_ID"));
            bus.setBus_type(rs.getString("BUS_TYPE"));
            bus.setColor(rs.getString("Color"));
        }
        rs.close();
        ps.close();
        conn.close();
        return bus;
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
            Bus busPr = getBusById(rs.getInt("BUS_PR"));
            Bus busRe = getBusById(rs.getInt("BUS_RE"));
            DrLigne ligne = getLigneById(rs.getInt("DENUMLI"));
            trip.setStopTimesList(stopTimesList);
            trip.setBusPr(busPr);
            trip.setBusRe(busRe);
            trip.setLigne(ligne);
            tripsList.add(trip);
        }
        rs.close();
        ps.close();
        conn.close();
        return tripsList;
    }

}