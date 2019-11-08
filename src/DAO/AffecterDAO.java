package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionSingleton;
import Metier.Affecter;

public class AffecterDAO {

	public AffecterDAO() {

	}
	
	public List<Affecter> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        List<Affecter> allAffecter = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numTab, dataff, numserv FROM affecter" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allAffecter.add(new Affecter(res.getInt("numTab"),res.getString("dataff"),res.getInt("numserv")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAffecter;
    }

    public Affecter find(int id, String dateID){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        Affecter affecter = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numTab, dataff, numserv FROM affecter WHERE numTab=? AND dataff=?" );
            ps.setInt(0,id);
            ps.setString(1, dateID);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                affecter = new Affecter(res.getInt(0), res.getString(1), res.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affecter;
    }

}
