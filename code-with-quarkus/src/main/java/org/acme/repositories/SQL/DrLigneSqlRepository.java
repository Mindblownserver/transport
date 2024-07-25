package org.acme.repositories.SQL;

import org.acme.Embeddable.StopTimesId;
import org.acme.Embeddable.TripsId;
import org.acme.entities.DrCentre;
import org.acme.entities.DrStati;
import org.acme.entities.DrTypeLigne;
import org.acme.entities.SQL.DrItinSql;
import org.acme.entities.SQL.DrLigneSql;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entities.SQL.TripsSql;
import org.acme.entities.StopTimes;
import org.acme.repositories.DrCentreRepository;
import org.acme.repositories.DrDelegRepository;
import org.acme.repositories.DrTypeLigneRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class DrLigneSqlRepository {
    @Inject
    DataSource dataSource;
    @Inject
    DrItinSqlRepository drItinSqlRepository;

    public List<DrLigneSql> listAll() throws SQLException {
        List<DrLigneSql> lignesules = new ArrayList<>();
        String sql = "SELECT * FROM DRLIGNE";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DrLigneSql lignes = new DrLigneSql();
                lignes.setIdLigne(rs.getLong("DENUMLI"));//// ahayya
                lignes.setNomLigne(rs.getString("DENOMLI"));
                lignes.setPriorite(rs.getLong("DEPRIOR"));
                lignes.setTyTa(rs.getString("DECTYTA"));
                lignes.setTyEq(rs.getInt("DECTYEQ"));
                lignes.setNomLa(rs.getString("DENOMLA"));
                lignes.setTaTec(rs.getDate("DEDATEC"));
                lignes.setTaTea(rs.getDate("DEDATEA"));
                lignes.setStatu(rs.getString("DESTATU"));
                lignes.setNbrkm(rs.getInt("DENBRKM"));
                lignes.setActif(rs.getInt("DEACTIF"));
                lignes.setAgencyId(rs.getString("AGENCY_ID"));
                lignes.setRouteType(rs.getLong("ROUTE_TYPE"));
                lignes.setRouteUrl(rs.getString("ROUTE_URL"));
                lignes.setRouteColor(rs.getInt("ROUTE_COLOR"));
                lignes.setRouteTextColor(rs.getLong("ROUTE_TEXT_COLOR"));
                lignes.setNomLiRet(rs.getString("DENOMLI_RET"));
                lignes.setIntegBi(rs.getInt("INTEG_BI"));
                lignes.setClie(rs.getLong("DECCLIE"));
                lignes.setAdmi(rs.getInt("DECADMI"));
                lignes.setSae(rs.getInt("SAE"));

                DrTypeLigne typeligne = getTypeLigneById(rs.getLong("DECTYLI"));
                DrCentre centres = getCentreById(rs.getLong("DECCENT"));

                List <DrItinSql> itins = drItinSqlRepository.getItinByNumli(rs.getLong("DENUMLI"));

                lignes.setType(typeligne);
                lignes.setCentre(centres);
                lignes.setItinList(itins);

                //lignes.setCentre(drCentreRepository.getDrCentreById(rs.getInt("DECCENT")));
                //lignes.setDeleg(drDelegRepository.getDrDelegById(rs.getInt("DECDELEG")));


                lignesules.add(lignes);
            }
        }

        return lignesules;
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

    private DrCentre getCentreById(long decCentre) throws SQLException {
        DrCentre centre = new DrCentre();
        String sql = "select * from DRCENTRE where DECCENT = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,decCentre);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    centre.setDec_centre((long)decCentre);
                    centre.setDel_centre(rs.getString("DELCENT"));
                    centre.setAr_centre(rs.getString("AR_DELCENT"));
                }
                return centre;
            }
        }
    }
}