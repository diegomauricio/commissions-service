/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author floresdi
 */
public class SqlServerConnection {

    public static Connection getConnection(String dbURL, String user, String pass) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            conn = null;
        }
        return conn;

    }

}
