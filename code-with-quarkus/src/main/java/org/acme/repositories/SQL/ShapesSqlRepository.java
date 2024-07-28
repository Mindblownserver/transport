package org.acme.repositories.SQL;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entities.SQL.ShapesSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ShapesSqlRepository {
    @Inject
    DataSource dataSource;

    public List<ShapesSql> getAll() throws SQLException {
        List<ShapesSql> shapes = new ArrayList<>();
        String sql = "SELECT * FROM SHAPE";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ShapesSql shape = new ShapesSql();
                shape.setDeNumLi(rs.getLong("DENUMLI"));
                shape.setN(rs.getInt("N"));
                shape.setDIRECTION_ID(rs.getLong("DIRECTION_ID"));
                shape.setLat(rs.getLong("LAT"));
                shape.setLon(rs.getLong("LON"));
                shape.setMaxVitesse(rs.getInt("MAX_VITESSE"));
                shape.setKm(rs.getInt("KM"));
                shapes.add(shape);
            }
        }

        return shapes;
    }

    public List<ShapesSql> getShapsByNumli(long numli) throws SQLException {
        List<ShapesSql> shapes = new ArrayList<>();
        String sql = "SELECT * FROM SHAPE where DENUMLI = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,numli);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    ShapesSql shape = new ShapesSql();
                    shape.setDeNumLi(rs.getLong("DENUMLI"));
                    shape.setN(rs.getInt("N"));
                    shape.setDIRECTION_ID(rs.getLong("DIRECTION_ID"));
                    shape.setLat(rs.getLong("LAT"));
                    shape.setLon(rs.getLong("LON"));
                    shape.setMaxVitesse(rs.getInt("MAX_VITESSE"));
                    shape.setKm(rs.getInt("KM"));
                    shapes.add(shape);
                }
            }
        }

        return shapes;
    }
}
