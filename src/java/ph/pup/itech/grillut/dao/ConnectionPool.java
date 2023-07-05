/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.pup.itech.grillut.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ze Familee
 */
public class ConnectionPool {
    
    static Connection conn;
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            String url = "jdbc:mysql://localhost:3306/grillut?serverTimezone=UTC";
            conn = (Connection) DriverManager.getConnection(url, "root", "Iamanintrovert21");
            
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        
        return conn;
    }
    
}
