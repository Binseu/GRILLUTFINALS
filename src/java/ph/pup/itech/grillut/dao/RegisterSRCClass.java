/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ph.pup.itech.grillut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ph.pup.itech.grillut.model.RegisterModel;

/**
 *
 * @author LAPTOP - Binseu
 */
public class RegisterSRCClass {

    public ArrayList<RegisterModel> getallRegister() throws ClassNotFoundException {
        ArrayList<RegisterModel> allRegister = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT "
                    + "registerFirstName, "
                    + "registerLastName, "
                    + "registerMiddleName, "
                    + "registerUsername, "
                    + "registerPassword, "
                    + "registerRepeatPassword,"
                    + "registerAddress"
                    + "registerBrithday"
                    + "registerNumber "
                    + "FROM clientregform";

            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                RegisterModel register = new RegisterModel();
                register.setregisterFirstName(rs.getString("registerFirstName"));
                register.setregisterLastName(rs.getString("registerLastName"));
                register.setregisterMiddleName(rs.getString("registerMiddleName"));
                register.setregisterUsername(rs.getString("registerUsername"));
                register.setregisterPassword(rs.getString("registerPassword"));
                register.setregisterRepeatPassword(rs.getString("registerRepeatPassword"));
                register.setregisterAddress(rs.getString("registerAddress"));
                register.setregisterFirstName(rs.getString("registerBrithday"));
                register.setregisterNumber(rs.getString("registerNumber"));
                allRegister.add(register);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("getallRegister error" + e);
        } finally {                       
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("SQLException " + e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("SQLException " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("SQLException " + e.getMessage());
                }
            }

        }
        
        System.out.println(allRegister);
        return allRegister;
    }

}