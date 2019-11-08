package DAO;

import Database.ConnectionSingleton;
import Metier.Plat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatDAO {
	
    public PlatDAO(){

    }

    public List<Plat> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        List<Plat> allPlat = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numplat, libelle, type, prixunit FROM plat" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allPlat.add(new Plat(res.getInt("numplat"),res.getString("libelle"),res.getString("type"),res.getDouble("prixunit")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlat;
    }

    public Plat find(int id){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        Plat plat = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numplat, libelle, type, prixunit FROM plat WHERE numplat=?" );
            ps.setInt(0,id);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                plat = new Plat(res.getInt(0), res.getString(1), res.getString(2), res.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plat;
    }

}
