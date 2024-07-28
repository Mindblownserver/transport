package org.acme.repositories.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.acme.Embeddable.DrItinIdRecord;
import org.acme.Embeddable.ShapsId;
import org.acme.entities.DrCentre;
import org.acme.entities.DrItin;
import org.acme.entities.DrLigne;
import org.acme.entities.DrStati;
import org.acme.entities.DrTypeLigne;
import org.acme.entities.SHAPS;

import java.sql.Connection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DrLigneSqlRespository {
    @Inject
    DataSource dataSource;

    public List<DrLigne> getLignes() throws SQLException {
        List<DrLigne> ligneListe = new ArrayList<>();
        String sql = "SELECT * FROM DRLIGNE";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DrLigne ligne = new DrLigne();
                ligne.setIdLigne(rs.getLong("DENUMLI"));//// ahayya
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

                ligne.setDecCentre(rs.getInt("DECCENT"));
                ligne.setDecDeleg(rs.getInt("DECDELEG"));

                ligne.setType(getTypeLigneById(rs.getLong("DECTYLI")));

                ligne.setItinList(getItinListByLigneId(rs.getInt("DENUMLI")));

                ligne.setShapList(getShapesByLigneId(rs.getInt("DENUMLI")));
                ligneListe.add(ligne);
            }
        }

        return ligneListe;
    }

    public List<DrItin> getItinListByLigneId(int denumli) throws SQLException {
        List<DrItin> itins = new ArrayList<>();
        String sql = "SELECT * FROM DRITIN where DENUMLI = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,denumli);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    DrItin itin = new DrItin();
                    itin.setId(new DrItinIdRecord((long)denumli, rs.getLong("DENUMLG")));
                    itin.setKmsTa(rs.getInt("DEKMSTA"));
                    itin.setDureetr(rs.getInt("DEDURTR"));
                    itin.setEscale(rs.getInt("DEESCALE"));
                    itin.setDates(rs.getString("DEDATES"));
                    itin.setTarif(rs.getInt("DETARIF"));
                    itin.setStat(getStationById(rs.getInt("DECSTAT")));
                    itins.add(itin);
                    
                }
            }
        }

        return itins;
    }

    private DrTypeLigne getTypeLigneById(long dectyli) throws SQLException {
        DrTypeLigne typeligne = new DrTypeLigne();
        String sql = "select * from DRTYPLI where DECTYLI = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,dectyli);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    typeligne.setDec_tpli((long)dectyli);
                    typeligne.setFr_tpli(rs.getString("DELTYLI"));
                    typeligne.setAr_tpli(rs.getString("DELTYLA"));
                }
                return typeligne;
            }
        }
    }

    private List<SHAPS> getShapesByLigneId(int denumli) throws SQLException{
        List<SHAPS> shapList = new ArrayList<>();
        String sql = "SELECT * FROM SHAPE where DENUMLI = ? order by N asc";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,denumli);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    SHAPS shap = new SHAPS();
                    shap.setIdShaps(new ShapsId(rs.getLong("denumli"), rs.getInt("N"), rs.getInt("DIRECTION_ID")));
                    shap.setLAT(rs.getDouble("LAT"));
                    shap.setLON(rs.getDouble("LON"));
                    shapList.add(shap);
                }
            }
        }

        return shapList;
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
                    station.setLatitude(rs.getFloat("STOP_LAT"));
                    station.setLongetude(rs.getFloat("STOP_LON"));

                }
            }
        }
        return station;
    }   
}