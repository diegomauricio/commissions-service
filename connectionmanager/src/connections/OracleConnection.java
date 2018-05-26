/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 *
 * @author floresdi
 */
public class OracleConnection {

    public static Connection getConnection(String url, String user, String pass) {
        try {
            String driver = "oracle.jdbc.OracleDriver";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (Exception e) {
            return null;
        }
    }

}
