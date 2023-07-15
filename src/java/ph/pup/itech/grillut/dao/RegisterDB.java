/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ph.pup.itech.grillut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LAPTOP - Binseu
 */
public class RegisterDB {

    public boolean RegisterForm(
            String registerFirstName,
            String registerMiddleName,
            String registerLastName,
            String registerUsername,
            String registerPassword,
            String registerAddress,
            String registerBirthday,
            String registerNumber) throws ClassNotFoundException {

        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            String query = "INSERT INTO clientuserform ("
                    + "registerFirstName,"
                    + "registerMiddleName,"
                    + "registerLastName,"
                    + "registerUsername,"
                    + "registerPassword"
                    + "registerAddress"
                    + "registerBirthday"
                    + "registerNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, registerFirstName);
            ps.setString(2, registerMiddleName);
            ps.setString(3, registerLastName);
            ps.setString(4, registerUsername);
            ps.setString(5, registerPassword);
            ps.setString(6, registerAddress);
            ps.setString(7, registerBirthday);
            ps.setString(8, registerNumber);

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
