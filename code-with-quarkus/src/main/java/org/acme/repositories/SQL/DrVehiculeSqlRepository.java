package org.acme.repositories.SQL;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entities.SQL.AgentSql;
import org.acme.entities.SQL.DrVehiculeSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DrVehiculeSqlRepository {
    @Inject
    DataSource dataSource;

    public List<DrVehiculeSql> findAll() throws SQLException {
        List<DrVehiculeSql> vehicules = new ArrayList<>();
        String sql = "SELECT * FROM DRVEHIC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DrVehiculeSql vehic = new DrVehiculeSql();
                vehic.setDecodvh(rs.getLong("DECODVH"));
                vehic.setDematri(rs.getString("DEMATRI"));
                vehic.setDecatvh(rs.getInt("DECATVH"));
                vehic.setDeccent(rs.getInt("DECCENT"));
                vehic.setDecdeleg(rs.getInt("DECDELEG"));
                vehicules.add(vehic);
            }
        }

        return vehicules;
    }

    public DrVehiculeSql findByDec(long dec)throws SQLException {
        DrVehiculeSql vehic = new DrVehiculeSql();
        String sql = "select * from DRVEHIC where DECODVH = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, dec);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehic.setDecodvh(dec);
                    vehic.setDematri(rs.getString("DEMATRI"));
                    vehic.setDecatvh(rs.getInt("DECATVH"));
                    vehic.setDeccent(rs.getInt("DECCENT"));
                    vehic.setDecdeleg(rs.getInt("DECDELEG"));
                }
                return vehic;
            }
        }
    }

    public DrVehiculeSql findByMatricule(String matric)throws SQLException {
        DrVehiculeSql vehic = new DrVehiculeSql();
        String sql = "select * from DRVEHIC where DEMATRI = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, matric);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehic.setDematri(matric);
                    vehic.setDecodvh(rs.getLong("DECODVH"));
                    vehic.setDecatvh(rs.getInt("DECATVH"));
                    vehic.setDeccent(rs.getInt("DECCENT"));
                    vehic.setDecdeleg(rs.getInt("DECDELEG"));
                }
                return vehic;
            }
        }
    }
}