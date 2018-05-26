/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

/**
 *
 * @author floresdi
 */
import java.sql.DriverManager;
import java.sql.Connection;

public class PostgreConnection {

    public static Connection getConnection(String url, String user, String pass) {
        try {            
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            Connection con = null;
            con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (Exception e) {
            return null;
        }
    }

}