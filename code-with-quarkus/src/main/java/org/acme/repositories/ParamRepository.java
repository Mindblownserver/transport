package org.acme.repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.acme.entities.Agent;
import org.acme.entities.DrCentre;
import org.acme.entities.DrDeleg;
import org.acme.entities.DrStati;
import org.acme.entities.DrTypeLigne;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ParamRepository {
    @Inject
    DataSource dataSource;

    public List<Agent> getAgents()throws SQLException{
        List<Agent> agentList=  new ArrayList<>();
        String sql = "select DECAGEN, DECDELEG, DENAGEA, DENAGEN from DrAgent";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Agent agent=new Agent();
                        agent.setDecagen(rs.getInt("DECAGEN"));
                        agent.setDecdeleg(rs.getInt("DECDELEG"));
                        agent.setDenagea(rs.getString("DENAGEA"));
                        agent.setDenagen(rs.getString("DENAGEN"));
                        agentList.add(agent);
                    }
                }
        } 
        return agentList;
    }

    public List<DrCentre> getCentres()throws SQLException{
        List<DrCentre> centreList = new ArrayList<>();

        String sql = "select DECCENT, DELCENT, AR_DELCENT from DRCENTR"; // DRCENTR
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        DrCentre centre = new DrCentre();
                        centre.setDec_centre(rs.getInt("DECCENT"));
                        centre.setDel_centre(rs.getString("DELCENT"));
                        centre.setAr_centre(rs.getString("AR_DELCENT"));
                        centreList.add(centre);
                    }
                }
        } 
        return centreList;
    }

    public List<DrStati> getStations() throws SQLException{
        List<DrStati> stationList = new ArrayList<>();
        String sql = "SELECT DECSTAT, DELSTAT, DELSTAA, DATE_SYS, DECTYST, STOP_LAT, STOP_LON, RAYON from DRSTATI";
        
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DrStati station = new DrStati();
                    station.setDecStat(rs.getInt("DECSTAT"));
                    station.setNomFr(rs.getString("DELSTAT"));
                    station.setNomAr(rs.getString("DELSTAA"));
                    station.setDate(rs.getTimestamp("DATE_SYS"));
                    station.setTypeSt(rs.getInt("DECTYST"));
                    station.setLatitude(rs.getFloat("STOP_LAT"));
                    station.setLongetude(rs.getFloat("STOP_LON"));
                    station.setRayon(rs.getInt("RAYON"));
                    stationList.add(station);
                }
            }
        }
        return stationList;
    }

    public List<DrDeleg> getDelegues()throws SQLException{
        List<DrDeleg> delegList = new ArrayList<>();
        String sql = "select DECDELEG, LIBDELEGAR, LIBDELEGFR from DRDELEG";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DrDeleg deleg = new DrDeleg();
                    deleg.setDec_deleg(rs.getInt("DECDELEG"));
                    deleg.setAr_deleg(rs.getString("LIBDELEGAR"));
                    deleg.setFr_deleg(rs.getString("LIBDELEGFR"));
                    delegList.add(deleg);
                }
            }
        } 
        return delegList;
    }

    public List<DrTypeLigne> getTypeLignes() throws SQLException{
        List<DrTypeLigne> typeLigneList = new ArrayList<>();
        String sql = "select DECTYLI, DELTYLI, DELTYLA from DRTYPLI";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DrTypeLigne typeligne = new DrTypeLigne();
                    typeligne.setDec_tpli(rs.getInt("DECTYLI"));
                    typeligne.setFr_tpli(rs.getString("DELTYLI"));
                    typeligne.setAr_tpli(rs.getString("DELTYLA"));
                    typeLigneList.add(typeligne);
                }
                return typeLigneList;
            }
        }
    }

}
