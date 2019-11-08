package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionSingleton;
import Metier.TypePlat;

public class TypePlatDAO {
    
	public TypePlatDAO(){

    }

    public List<TypePlat> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        List<TypePlat> allPlat = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numTypePlat, nomTypePlat FROM typeplat" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allPlat.add(new TypePlat(res.getInt("numTypePlat"),res.getString("nomTypePlat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlat;
    }

    public TypePlat find(int id){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        TypePlat typePlat = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numplat, libelle FROM plat WHERE numplat=?" );
            ps.setInt(0,id);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                typePlat = new TypePlat(res.getInt(0), res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typePlat;
    }

}
