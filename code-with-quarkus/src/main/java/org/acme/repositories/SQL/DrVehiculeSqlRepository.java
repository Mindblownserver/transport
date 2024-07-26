package org.acme.repositories.SQL;

import javax.sql.DataSource;

import org.acme.entities.DrVehicule;
import org.acme.entities.TypeVehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.ResultSet;

@ApplicationScoped
public class DrVehiculeSqlRepository {
    @Inject
    DataSource datasource;

    public List<DrVehicule> getVehicules()throws SQLException{
        List<DrVehicule> vehicList = new ArrayList<>();
        DrVehicule vehic;
        String sql = "Select * from DRVEHIC";
        try(Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    vehic = new DrVehicule();
                    vehic.setDecodvh(rs.getLong("DECODVH"));
                    vehic.setDematri(rs.getString("DEMATRI"));
                    vehic.setDecdeleg(rs.getInt("DECDELEG"));
                    vehic.setDeccent(rs.getInt("DECCENT"));
                    vehic.setDecatvh(getTypeVehiculeByIdVehic(rs.getInt("DECATVH")));
                    vehicList.add(vehic);
                }
                return vehicList;
        }
    }

    private TypeVehicule getTypeVehiculeByIdVehic(int decatvh) throws SQLException{
        TypeVehicule type = new TypeVehicule();
        String sql = "select * from DRCATVE where DECATVH=?";
        try(Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, decatvh);
                ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        type.setId(decatvh);
                        type.setDeacate(rs.getString("DEACATE"));
                        type.setDecateg(rs.getString("DECATEG"));
                        type.setDenbplc(rs.getInt("DENBPLC"));
                        type.setDureeVie(rs.getLong("DUREE_VIE"));
                        type.setPrixIm(rs.getLong("PRIXIM"));
                        type.setPrixKm(rs.getFloat("PRIXKM"));
                        type.setColor(rs.getString("COLORTYPE"));
                    }
                    return type;
            }
    }
}
