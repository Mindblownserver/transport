package org.acme.repositories.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.acme.entities.Agent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AgentSqlRepository {
    @Inject
    DataSource dataSource;

    public List<Agent> getAgents()throws SQLException{
        List<Agent> agentList=  new ArrayList<>();
        String sql = "select * from DrAgent";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Agent agent=new Agent();
                        agent.setDecagen(rs.getLong("DECAGEN"));
                        agent.setDecdeleg(rs.getInt("DECDELEG"));
                        agent.setDenagea(rs.getString("DENAGEA"));
                        agent.setDenagen(rs.getString("DENAGEN"));
                        agentList.add(agent);
                    }
                }
        } 
        return agentList;
    }
}
