package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Connexion.IDBDD;


public class ConnectionSingleton {
    private Connection c;
    private static ConnectionSingleton s;

    private ConnectionSingleton(){
        try{
        	IDBDD id = new IDBDD();
        	
            c = DriverManager.getConnection(id.getUrl(),id.getId(),id.getMdp());
        }catch(SQLException e){
            System.out.println(e);
        }
    }


    private ConnectionSingleton(String uRL, String iD, String mDP) {
        try{
            c = DriverManager.getConnection(uRL,iD,mDP);
        }catch(SQLException e){
            System.out.println(e);
        }
	}


	public synchronized static ConnectionSingleton getInstance(){
        if(s == null){
            s = new ConnectionSingleton();
        }else {
            try {
                if(s.c!=null && s.c.isClosed()){
                    s = new ConnectionSingleton();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    
    public synchronized static ConnectionSingleton getInstance(String uRL,String iD,String mDP){
        if(s == null){
            s = new ConnectionSingleton();
        }else {
            try {
                if(s.c!=null && s.c.isClosed()){
                    s = new ConnectionSingleton(uRL, iD, mDP);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public Connection getConnection(){
        return c;
    }
}
