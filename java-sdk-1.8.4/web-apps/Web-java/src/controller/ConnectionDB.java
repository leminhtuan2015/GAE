package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
	public static String driver = "com.mysql.jdbc.Driver";
//	public static String url = "jdbc:mysql://localhost:3306/webproject";
    public static String url = "http://ec2-54-234-104-107.compute-1.amazonaws.com:3306/bh_test";
//    public static String url = "http://localhost:3306/bh_test";
    public static String user = "root";
    public static String pass = "";
    public static Connection cnn;

    public static boolean open() {
        try {
            if (cnn == null || cnn.isClosed()) {
            	Class.forName(driver).newInstance();
                cnn =  DriverManager.getConnection(url, user, pass);
              System.out.println("open database success");
            }
            System.out.println("opened database");
              return true;

        } catch (Exception ex) {
            System.err.print(ex);
            System.out.println("open database error");
            return false;
        }
        
    }//end open()

    public static void close() {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (Exception ex) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//end Close()

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }//end

    public static void close(PreparedStatement ps,ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        close(ps);
    }//end

}
