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
import ph.pup.itech.grillut.model.UserModel;

/**
 *
 * @author LAPTOP - Binseu
 */
public class UserSRCClass {

    public ArrayList<UserModel> getallUser() throws ClassNotFoundException {
        ArrayList<UserModel> allUsers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT"
                    + "userID,"
                    + "userFirstName,"
                    + "userMiddleName,"
                    + "userLastName,"
                    + "userRole) "
                    + "FROM clientuserform";

            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setuserFirstName(rs.getString("userFirstName"));
                user.setuserMiddleName(rs.getString("userMiddleName"));
                user.setuserLastName(rs.getString("userLastName"));
                user.setuserRole(rs.getString("userRole"));
                user.setuserID(rs.getString("userID"));
                allUsers.add(user);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("getallUser error" + e);
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

        return allUsers;
    }

}
