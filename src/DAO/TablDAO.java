package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectionSingleton;
import Metier.Tabl;

public class TablDAO {

	public TablDAO() {
		
	}
	
    public List<Tabl> getAll(){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        
        List<Tabl> allTabl = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numtab, nbplace FROM tabl" );
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                allTabl.add(new Tabl(res.getInt("numtab"),res.getInt("nbplace")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTabl;
    }

    public Tabl find(int id){
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        Connection c = cs.getConnection();
        Tabl tabl= null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT numtab, nbpers FROM tabl WHERE numtab=?");
            ps.setInt(0,id);
            ResultSet res = ps.executeQuery();
            if(res.first()) {
                tabl = new Tabl(res.getInt(0), res.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tabl;
    }
}
