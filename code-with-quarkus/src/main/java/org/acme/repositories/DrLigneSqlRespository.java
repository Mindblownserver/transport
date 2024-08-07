package org.acme.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.acme.Embeddable.DrItinIdRecord;
import org.acme.Embeddable.ShapsId;
import org.acme.entities.DrItin;
import org.acme.entities.DrLigne;
import org.acme.entities.DrTypeLigne;
import org.acme.entities.Shapes;

import java.sql.Connection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DrLigneSqlRespository {
    @Inject
    DataSource dataSource;

    public List<DrLigne> getLignes() throws SQLException {
        List<DrLigne> ligneListe = new ArrayList<>();
        String sql = "SELECT DENUMLI, DENOMLI, DECTYLI, DEPRIOR, DECTYTA, DECTYEQ, DECCENT, DENOMLA, DEDATEC, DEDATEA, DESTATU, DENBRKM, DECDELEG, DEACTIF, AGENCY_ID, ROUTE_TYPE, ROUTE_URL, ROUTE_COLOR, ROUTE_TEXT_COLOR, DENOMLI_RET, INTEG_BI, DECCLIE, DECADMI, SAE FROM DrLigne";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DrLigne ligne = new DrLigne();
                ligne.setIdLigne(rs.getInt("DENUMLI"));//// ahayya
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

                ligne.setType(getTypeLigneById(rs.getInt("DECTYLI")));

                ligne.setItinList(getItinListByLigneId(rs.getInt("DENUMLI")));

                ligne.setShapList(getShapesByLigneId(rs.getInt("DENUMLI")));
                ligneListe.add(ligne);
            }
        }

        return ligneListe;
    }

    public List<DrItin> getItinListByLigneId(int denumli) throws SQLException {
        List<DrItin> itins = new ArrayList<>();
        String sql = "SELECT DEKMSTA, DENUMLG, DEDURTR, DEESCALE, DEDATES, DETARIF, DECSTAT FROM DRITIN where DENUMLI = ?";

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
                    itin.setStat(GeneralSqlOperations.getStationById(rs.getInt("DECSTAT"),dataSource));
                    itins.add(itin);
                    
                }
            }
        }

        return itins;
    }

    private DrTypeLigne getTypeLigneById(int dectyli) throws SQLException {
        DrTypeLigne typeligne = new DrTypeLigne();
        String sql = "select DELTYLI, DELTYLA from DRTYPLI where DECTYLI = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,dectyli);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    typeligne.setDec_tpli(dectyli);
                    typeligne.setFr_tpli(rs.getString("DELTYLI"));
                    typeligne.setAr_tpli(rs.getString("DELTYLA"));
                }
                return typeligne;
            }
        }
    }

    private List<Shapes> getShapesByLigneId(int denumli) throws SQLException{
        List<Shapes> shapList = new ArrayList<>();
        String sql = "SELECT denumli, N, DIRECTION_ID, LAT, LON FROM SHAPE where DENUMLI = ? order by N asc";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,denumli);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Shapes shap = new Shapes();
                    shap.setIdShaps(new ShapsId(rs.getLong("denumli"), rs.getInt("N"), rs.getInt("DIRECTION_ID")));
                    shap.setLAT(rs.getDouble("LAT"));
                    shap.setLON(rs.getDouble("LON"));
                    shapList.add(shap);
                }
            }
        }

        return shapList;
    }
}