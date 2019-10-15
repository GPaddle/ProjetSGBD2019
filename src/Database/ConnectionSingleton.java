package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSingleton {
    private Connection c;
    private static ConnectionSingleton s;

    private ConnectionSingleton(){
        try{
            String url = "jdbc:mysql://127.0.0.1:3306/goodfood";
            c= DriverManager.getConnection(url,"root", "");
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
