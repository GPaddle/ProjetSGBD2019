package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionSingleton;
import Metier.Contient;

public class ContientDAO {

	public ContientDAO() {

	}
	
	public List<Contient> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        List<Contient> allContient = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numcom, numplat, quantite FROM contient" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allContient.add(new Contient(res.getInt("numcom"),res.getInt("numplat"),res.getInt("quantite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allContient;
    }

    public Contient find(int id, String platID){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        Contient contient = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numcom, numplat, quantite FROM contient WHERE numcom=? AND numplat=?" );
            ps.setInt(0,id);
            ps.setString(1, platID);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                contient = new Contient(res.getInt(0), res.getInt(1), res.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contient;
    }
}