package org.acme.repositories.SQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.acme.Embeddable.StopTimesId;
import org.acme.Embeddable.TripsId;
import org.acme.entities.Bus;
import org.acme.entities.DrLigne;
import org.acme.entities.DrStati;
import org.acme.entities.SQL.AgentSql;
import org.acme.entities.StopTimes;
import org.acme.entities.SQL.TripsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AgentSqlRepository {
    @Inject
    DataSource dataSource;

    public AgentSql findByDec(long dec)throws SQLException {
        AgentSql agent = new AgentSql();
        String sql = "select * from DrAgent where decagen = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, dec);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    agent.setDecagen((long) dec);
                    agent.setDenagea(rs.getString("DENAGEA"));
                    agent.setDenagen(rs.getString("DENAGEN"));
                    agent.setDecdeleg(rs.getInt("DECDELEG"));
                }
                return agent;
            }
        }
    }
}