package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Connexion.ID_BDDLocal;


public class ConnectionSingleton {
    private Connection c;
    private static ConnectionSingleton s;

    private ConnectionSingleton(){
        try{
        	ID_BDDLocal id = new ID_BDDLocal();
        	
            c= DriverManager.getConnection(id.getUrl(),id.getId(),id.getMdp());
        }catch(SQLException e){
            System.out.println(e);
        }
    }


    public static ConnectionSingleton getInstance(){
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

    public Connection getConnection(){
        return c;
    }
}
