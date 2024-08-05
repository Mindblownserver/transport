package org.acme.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.acme.entities.DrCentre;
import org.acme.entities.DrDeleg;
import org.acme.entities.DrStati;
import org.acme.entities.TypeVehicule;

import javax.sql.DataSource;

public class GeneralSqlOperations {
    
    public static DrStati getStationById(int decStat, DataSource dataSource) throws SQLException{
        DrStati station = new DrStati();
        String sql = "SELECT DELSTAT, DELSTAA, DATE_SYS, DECTYST, STOP_LAT, STOP_LON, RAYON from DRSTATI where DECSTAT=?";
        
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, decStat);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    station.setDecStat(decStat);
                    station.setNomFr(rs.getString("DELSTAT"));
                    station.setNomAr(rs.getString("DELSTAA"));
                    station.setDate(rs.getTimestamp("DATE_SYS"));
                    station.setTypeSt(rs.getInt("DECTYST"));
                    station.setLatitude(rs.getFloat("STOP_LAT"));
                    station.setLongetude(rs.getFloat("STOP_LON"));
                    station.setRayon(rs.getInt("RAYON"));
                }
            }
        }
        return station;
    }

    public static DrCentre getCentreById(int deccent, DataSource dataSource)throws SQLException{
        DrCentre centre = new DrCentre();
        String sql = "select DELCENT, AR_DELCENT from DRCENTR where deccent=?"; // DRCENTR
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, deccent);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        centre.setDec_centre(deccent);
                        centre.setDel_centre(rs.getString("DELCENT"));
                        centre.setAr_centre(rs.getString("AR_DELCENT"));
                    }
                }
        } 
        return centre;
    }

    public static DrDeleg getDelegById(int decdeleg, DataSource dataSource)throws SQLException{
        DrDeleg deleg = new DrDeleg();
        String sql = "select LIBDELEGAR, LIBDELEGFR from DRDELEG where decdeleg=?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, decdeleg);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        deleg.setDec_deleg(decdeleg);
                        deleg.setAr_deleg(rs.getString("LIBDELEGAR"));
                        deleg.setFr_deleg(rs.getString("LIBDELEGFR"));
                    }
                }
        } 
        return deleg;
    }
    
    public static TypeVehicule getTypeVehiculeById(int typeId, DataSource dataSource)throws SQLException{
        TypeVehicule type = new TypeVehicule();
        
        String sql = "select DEACATE, DECATEG, DENBPLC, DUREE_VIE, PRIXIM, PRIXKM, COLORTYPE from DrCatVe where DECATVH=?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, typeId);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        type.setId(typeId);
                        type.setDeacate(rs.getString("DEACATE"));
                        type.setDecateg(rs.getString("DECATEG"));
                        type.setDenbplc(rs.getInt("DENBPLC"));
                        type.setDureeVie(rs.getLong("DUREE_VIE"));
                        type.setPrixIm(rs.getLong("PRIXIM"));
                        type.setPrixKm(rs.getFloat("PRIXKM"));
                        type.setColor(rs.getString("COLORTYPE"));
                    }
                }
        }
        return type;
    }

}
