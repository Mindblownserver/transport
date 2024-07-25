package org.acme.repositories.SQL;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Embeddable.DrItinIdRecord;
import org.acme.entities.SQL.DrItinSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.inject.Inject;
@ApplicationScoped
public class DrItinSqlRepository{
    @Inject
    DataSource dataSource;
    @Inject
    DrStatiSqlRepository drStatiSqlRepository;

    public List<DrItinSql> getAll() throws SQLException {
        List<DrItinSql> itins = new ArrayList<>();
        String sql = "SELECT * FROM DRITIN";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DrItinSql itin = new DrItinSql();
                itin.setDeNumLi(rs.getLong("DENUMLI"));
                itin.setDeNumLg(rs.getLong("DENUMLG"));
                itin.setDeKmsTa(rs.getInt("DEKMSTA"));
                itin.setDeDureeTr(rs.getInt("DEDURTR"));
                itin.setDeEscale(rs.getInt("DEESCALE"));
                itin.setDeDates(rs.getString("DEDATES"));
                itin.setDeTarif(rs.getInt("DETARIF"));
                // Fetch the related entities if necessary
                //itin.setStation(drStatiSqlRepository.getDrStatiById(rs.getLong("DECSTAT")));
                itin.setStation(rs.getLong("DECSTAT"));
                itins.add(itin);
            }
        }

        return itins;
    }

    public List<DrItinSql> getItinByNumli(long numli) throws SQLException {
        List<DrItinSql> itins = new ArrayList<>();
        String sql = "SELECT * FROM DRITIN where DENUMLI = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,numli);
            try(ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                DrItinSql itin = new DrItinSql();
                itin.setDeNumLi(rs.getLong("DENUMLI"));
                itin.setDeNumLg(rs.getLong("DENUMLG"));
                itin.setDeKmsTa(rs.getInt("DEKMSTA"));
                itin.setDeDureeTr(rs.getInt("DEDURTR"));
                itin.setDeEscale(rs.getInt("DEESCALE"));
                itin.setDeDates(rs.getString("DEDATES"));
                itin.setDeTarif(rs.getInt("DETARIF"));
                // Fetch the related entities if necessary
                //itin.setStation(drStatiSqlRepository.getDrStatiById(rs.getLong("DECSTAT")));
                itin.setStation(rs.getLong("DECSTAT"));
                itins.add(itin);
            }
            }
        }

        return itins;
    }
}