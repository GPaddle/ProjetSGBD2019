package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionSingleton;
import Metier.Commande;

public class CommandeDAO {
	
	public CommandeDAO() {
	
	}
	
	public List<Commande> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        List<Commande> allCom = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numcom, numtab, datcom, nbpers, datpaie, modpaie, montcom FROM commande" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allCom.add(new Commande(res.getInt("numcom"),res.getInt("numtab"),res.getString("datcom"),res.getInt("nbpers"),res.getString("datpaie"),res.getString("modpaie"),res.getDouble("montcom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCom;
    }

    public Commande find(int id){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        Commande commande = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numcom, numtab, datcom, nbpers, datpaie, modpaie, montcom FROM commande WHERE numcom = ?" );
            ps.setInt(0,id);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                commande = new Commande(res.getInt(0), res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getDouble(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }
}
