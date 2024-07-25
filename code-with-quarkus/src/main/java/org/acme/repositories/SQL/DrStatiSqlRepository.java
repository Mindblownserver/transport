package org.acme.repositories.SQL;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.acme.entities.SQL.DrStatiSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class DrStatiSqlRepository {
    @Inject
    DataSource dataSource;
    public DrStatiSql getDrStatiById(long decStat) throws SQLException {
        DrStatiSql station= new DrStatiSql();
        Connection conn = dataSource.getConnection();
        String sql = "select * from DRSTATI where DECSTAT=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1,decStat);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            station.setNomFr(rs.getString("DELSTAT"));
            station.setNomAr(rs.getString("DELSTAA"));
            station.setTypeSt(rs.getInt("DECTYST"));
            station.setLatitude(rs.getFloat("STOP_LAT"));
            station.setLongetude(rs.getFloat("STOP_LON"));
            station.setDate(rs.getDate("DATE_SYS"));
            station.setRayon(rs.getInt("RAYON"));
        }
        rs.close();
        ps.close();
        conn.close();
        return station;
    }
}
