package org.acme.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.acme.Embeddable.StopTimesId;
import org.acme.Embeddable.TripsId;
import org.acme.entities.Agent;

import org.acme.entities.DrLigne;
import org.acme.entities.DrStati;
import org.acme.entities.DrVehicule;
import org.acme.entities.StopTimes;
import org.acme.entities.TypeVehicule;
import org.acme.entities.SQL.TripsSql;
import org.acme.resources.TripsResources;
import org.acme.resources.TripsResources.Statistics.StatisticsItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TripsSqlRepository {
    @Inject
    DataSource dataSource;

    public Agent getAgentById(int decAgen)throws SQLException{
        Agent agent = new Agent();
        String sql = "select DECDELEG, DENAGEA, DENAGEN from DrAgent where decagen=?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, decAgen);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        agent.setDecagen(decAgen);
                        agent.setDecdeleg(rs.getInt("DECDELEG"));
                        agent.setDenagea(rs.getString("DENAGEA"));
                        agent.setDenagen(rs.getString("DENAGEN"));
                    }
                }
        } 
        return agent;
    }

    public DrLigne getLigneById(int denumli)throws SQLException{
        DrLigne ligne = new DrLigne();
        String sql = "SELECT DENUMLI, DENOMLI, DECTYLI, DEPRIOR, DECTYTA, DECTYEQ, DECCENT, DENOMLA, DEDATEC, DEDATEA, DESTATU, DENBRKM, DECDELEG, DEACTIF, AGENCY_ID, ROUTE_TYPE, ROUTE_URL, ROUTE_COLOR, ROUTE_TEXT_COLOR, DENOMLI_RET, INTEG_BI, DECCLIE, DECADMI, SAE FROM DrLigne WHERE denumli = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1,denumli);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        ligne.setIdLigne(denumli);
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
                        ligne.setCentre(GeneralSqlOperations.getCentreById(rs.getInt("DECCENT"), dataSource));
                        ligne.setDeleg(GeneralSqlOperations.getDelegById(rs.getInt("DECDELEG"), dataSource));
                    }
                }
            }
            return ligne;
    }

    public List<StopTimes> getStopTimesByTripsId(Date dedated, int trip_id)throws SQLException{
        List<StopTimes> stopTimesList = new ArrayList<>();
        TripsId tipsId = new TripsId(dedated, trip_id);
        Connection conn = dataSource.getConnection();
        String sql = "SELECT DECSTAT, STOP_SEQUENCE, ARRIVAL_TIME, DEPARTURE_TIME, PICKUP_TYPE, SHAPE_DIST_TRAVELED, TIMEPOINT, RT_ARRIVAL_TIME,"+
        "RT_DEPARTURE_TIME, STATE FROM STOP_TIMES where trip_id=? and dedated = ? order by stop_sequence asc";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, trip_id);
        ps.setDate(2,new java.sql.Date(dedated.getTime()));
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            DrStati station = GeneralSqlOperations.getStationById(rs.getInt("DECSTAT"), dataSource);
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

    
    public DrVehicule getVehiculeById(int vehiculeId) throws SQLException{
        DrVehicule vehicule= new DrVehicule();
        Connection conn = dataSource.getConnection();
        String sql = "select DEMATRI, DECCENT, DECDELEG, DECATVH from DrVehic where DECODVH=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,vehiculeId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            vehicule.setDecodvh(vehiculeId);
            vehicule.setDematri(rs.getString("DEMATRI"));
            vehicule.setDeccent(rs.getInt("DECCENT"));
            vehicule.setDecdeleg(rs.getInt("DECDELEG"));
            TypeVehicule typeVehic = GeneralSqlOperations.getTypeVehiculeById(rs.getInt("DECATVH"),dataSource);
            vehicule.setDecatvh(typeVehic);
            
        }
        rs.close();
        ps.close();
        conn.close();
        return vehicule;
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
            rs.getInt("CHAUFF_RE"),
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
            DrVehicule busPr = getVehiculeById(rs.getInt("BUS_PR"));
            DrVehicule busRe = getVehiculeById(rs.getInt("BUS_RE"));
            DrLigne ligne = getLigneById(rs.getInt("DENUMLI"));
            Agent chauffPr = getAgentById(rs.getInt("CHAUFF_PR"));
            Agent recPr = getAgentById(rs.getInt("REC_PR"));
            trip.setStopTimesList(stopTimesList);
            trip.setBusPr(busPr);
            trip.setBusRe(busRe);
            trip.setLigne(ligne);
            trip.setRecPr(recPr);
            trip.setChauffPr(chauffPr);
            tripsList.add(trip);
        }
        rs.close();
        ps.close();
        conn.close();
        return tripsList;
    }

    public TripsResources.Statistics getTripsStatisticsByDate(Date date) throws SQLException {
        TripsResources.Statistics stat = new TripsResources.Statistics(0, 0, 0, 0, 0);
        String sqlVoyage = "select count(*) nbrVoyages from TRIPS where DEDATED=? and TRIP_NID is not null";
        String sqlAller = "select count(*) nbrAller from TRIPS where DEDATED=? and TRIP_NID is null and DIRECTION_ID=0";
        String sqlRetour= "select count(*) nbrRetour from TRIPS where DEDATED=? and TRIP_NID is null and DIRECTION_ID=1";
        String sqlNbrLignes = "select count(distinct denumli) nbrLignes from TRIPS where DEDATED=?";
        String sqlNbrVehicules = "select count(distinct bus_pr) nbrBus from TRIPS where DEDATED=?";
        String sqlNbrAgents= "select nature , count(*) agents from (select distinct chauff_pr ag , 'ch' nature from trips where dedated=? union select distinct rec_pr ag  , 'rec' nature from TRIPS where dedated=? ) group by nature";
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(sqlVoyage);
        ps.setDate(1, sqlDate);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            stat.setNbrVoyages(rs.getInt("nbrVoyages")/2);
        
        ps = conn.prepareStatement(sqlAller);
        ps.setDate(1,sqlDate);
        rs = ps.executeQuery();
        while(rs.next())
            stat.setNbrAller(rs.getInt("nbrAller"));
        
        ps = conn.prepareStatement(sqlRetour);
        ps.setDate(1,sqlDate);
        rs = ps.executeQuery();
        while(rs.next())
            stat.setNbrRetour(rs.getInt("nbrRetour"));

        ps = conn.prepareStatement(sqlNbrLignes);
        ps.setDate(1,sqlDate);
        rs = ps.executeQuery();
        while(rs.next())
            stat.setNbrLignesAffectes(rs.getInt("nbrLignes"));
        
        ps = conn.prepareStatement(sqlNbrVehicules);
        ps.setDate(1,sqlDate);
        rs = ps.executeQuery();
        while(rs.next())
            stat.setNbrBusAffectes(rs.getInt("nbrBus"));
        
        ps = conn.prepareStatement(sqlNbrAgents);
        ps.setDate(1,sqlDate);
        ps.setDate(2,sqlDate);
        rs = ps.executeQuery();
        int i=0;
        int[] agents=new int[2];
        while(rs.next()){
            agents[i] = rs.getInt("agents");
            i++;
        }
        stat.setNbrChauffRecAffectes(agents);

        stat.setLignesParCentre(getStatistiqueLigneParCentre(sqlDate));
        stat.setBusParCentre(getStatistiqueBusParCentre(sqlDate));
        stat.setAgentParCentre(getStatistiqueAgentParCentre(sqlDate));
        rs.close();ps.close();conn.close();
        return stat;
    }

    private List<StatisticsItem> getStatistiqueAgentParCentre(java.sql.Date sqlDate) throws SQLException{
        List<StatisticsItem> agentList = new ArrayList<>();
        String sql = "select l.deccent cent, count(distinct t.chauff_pr) nbrAgent from TRIPS t,DrLigne l where t.dedated=? and t.denumli= l.denumli group by l.deccent order by l.deccent asc";
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, sqlDate);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            agentList.add(new StatisticsItem(rs.getInt("cent"), rs.getInt("nbrAgent")));
        
        rs.close();
        ps.close();
        conn.close();
        return  agentList;
    }

    private List<StatisticsItem> getStatistiqueBusParCentre(java.sql.Date sqlDate)throws SQLException {
        List<StatisticsItem> busList = new ArrayList<>();
        String sql = "select v.deccent cent, count(distinct t.bus_pr) nbrBus from TRIPS t, DrVehic v where t.bus_pr=v.decodvh and t.dedated=? group by v.deccent order by v.deccent asc";
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, sqlDate);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            busList.add(new StatisticsItem(rs.getInt("cent"), rs.getInt("nbrBus")));
        
        rs.close();
        ps.close();
        conn.close();
        return  busList;
    }

    private List<StatisticsItem> getStatistiqueLigneParCentre(java.sql.Date sqlDate)throws SQLException {
        List<StatisticsItem> ligneList = new ArrayList<>();
        String sql = "select l.deccent cent, count(distinct t.denumli) nbrLigne from TRIPS t,DrLigne l where t.dedated=? and t.denumli= l.denumli group by l.deccent order by l.deccent asc";
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, sqlDate);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        ligneList.add(new StatisticsItem(rs.getInt("cent"), rs.getInt("nbrLigne")));
        
        rs.close();
        ps.close();
        conn.close();
        return  ligneList;    
    }

}