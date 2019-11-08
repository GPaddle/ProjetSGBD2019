package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionSingleton;
import Metier.Serveur;

public class ServeurDAO {

	public ServeurDAO() {
		
	}
	
	public List<Serveur> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        List<Serveur> allServeur = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numServ, nomServ, grade FROM serveur" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allServeur.add(new Serveur(res.getInt("numServ"),res.getString("nomServ"),res.getString("grade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allServeur;
    }

    public Serveur find(int id){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        Serveur serveur = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numserv, nomserv, grade FROM serveur WHERE numserv=?" );
            ps.setInt(0,id);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                serveur = new Serveur(res.getInt(0), res.getString(1), res.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serveur;
    }

}
