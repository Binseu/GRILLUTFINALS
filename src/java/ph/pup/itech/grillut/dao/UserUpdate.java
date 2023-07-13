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
 * @author Matic
 */
public class UserUpdate {

    public ArrayList<UserModel> getUserDetails(String id) throws ClassNotFoundException {
        ArrayList<UserModel> userDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT "
                    + "userID, "
                    + "userFirstName, "
                    + "userMiddleName, "
                    + "userLastName, "
                    + "userRole "
                    + "FROM clientuserform "
                    + "WHERE userID = ?";

            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setuserFirstName(rs.getString("userFirstName"));
                user.setuserMiddleName(rs.getString("userMiddleName"));
                user.setuserLastName(rs.getString("userLastName"));
                user.setuserRole(rs.getString("userRole"));
                user.setuserID(rs.getString("userID"));
                userDetails.add(user);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("getUserDetails error" + e);
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
        return userDetails;
    }

    public boolean editUser(
            String userID,
            String userFirstName,
            String userMiddleName,
            String userLastName,
            String userRole,
            String user_ID) throws ClassNotFoundException {

        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE clientuserform SET userID = ?, userFirstName = ?, userMiddleName = ?, userLastName = ?, userRole = ? WHERE userID = ? ";

//            System.out.println(userRole);
            
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, userFirstName);
            ps.setString(3, userMiddleName);
            ps.setString(4, userLastName);
            ps.setString(5, userRole);
            ps.setString(6, user_ID);
            

            int rowAffected = ps.executeUpdate();
            if (rowAffected != 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("getUserDetails error" + e);
        } finally {
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
        return success;
    }
    
    public boolean deleteUser(String userID) throws ClassNotFoundException {
        boolean success = false;
        Connection conn  = null;
        PreparedStatement ps = null;
        
        String query = "DELETE FROM clientuserform WHERE userID = ? ";
        try {
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userID);
            int rowAffected = ps.executeUpdate();
            if (rowAffected != 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("deleteUser Error: " + e); 
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            
        }
        return success;
    }
    
}
