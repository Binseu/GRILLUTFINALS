/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.pup.itech.grillut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ze Familee
 */
public class PrdFormDB {
    
        public boolean createPrdForm (       
        int productID,
        String productName,
        String productDescription,
        String productSize,
        double productPrice,
        int productQuantity) throws ClassNotFoundException{
        
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            
            String query = "INSERT INTO clientprdform ("
                    + "productID,"
                    + "productName,"
                    + "productDescription,"
                    + "productSize,"
                    + "productPrice,"
                    + "productQuantity) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, productID);
            ps.setString(2, productName);
            ps.setString(3, productDescription);
            ps.setString(4, productSize);
            ps.setDouble(5, productPrice);
            ps.setInt(6, productQuantity);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                success = true;
            }
            
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("SQLException " + e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("SQLException " + e.getMessage());
                }
            }
        }
        if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("SQLException " + e.getMessage());
                }
            }
        
        return success;
    }
        
}