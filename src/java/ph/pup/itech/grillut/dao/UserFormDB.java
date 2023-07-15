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
public class UserFormDB {

    public boolean UserForm(
            String userID,
            String userFirstName,
            String userMiddleName,
            String userLastName,
            String userRole) throws ClassNotFoundException {

        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            String query = "INSERT INTO clientuserform ("
                    + "userID,"
                    + "userFirstName,"
                    + "userMiddleName,"
                    + "userLastName,"
                    + "userRole) "
                    + "VALUES (?, ?, ?, ?, ?)";

            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, userID);
            ps.setString(2, userFirstName);
            ps.setString(3, userMiddleName);
            ps.setString(4, userLastName);
            ps.setString(5, userRole);

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
