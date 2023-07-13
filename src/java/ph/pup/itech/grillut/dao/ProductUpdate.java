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
import ph.pup.itech.grillut.model.ProductModel;

/**
 *
 * @author Matic
 */
public class ProductUpdate {

    public ArrayList<ProductModel> getProductDetails(String id) throws ClassNotFoundException {
        ArrayList<ProductModel> productDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT "
                    + "productID, "
                    + "productName, "
                    + "productDescription, "
                    + "productSize, "
                    + "productPrice, "
                    + "productQuantity "
                    + "FROM clientprdform "
                    + "WHERE productID = ?";

            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setproductID(rs.getInt("productID"));
                product.setproductName(rs.getString("productName"));
                product.setproductDescription(rs.getString("productDescription"));
                product.setproductSize(rs.getString("productSize"));
                product.setproductPrice(rs.getDouble("productPrice"));
                product.setproductQuantity(rs.getInt("productQuantity"));
                productDetails.add(product);
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
        return productDetails;
    }

    public boolean editProduct(
            int productID,
            String productName,
            String productDescription,
            String productSize,
            double productPrice,
            int productQuantity,
            int product_ID) throws ClassNotFoundException {

        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "UPDATE clientprdform SET productID = ?, productName = ?, productDescription = ?, productSize = ?, productPrice = ?, productQuantity = ? WHERE productID = ? ";
            
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setString(2, productName);
            ps.setString(3, productDescription);
            ps.setString(4, productSize);
            ps.setDouble(5, productPrice);
            ps.setInt(6, productQuantity);
            ps.setInt(7, product_ID);
            

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
    
    public boolean deleteProduct(int productID) throws ClassNotFoundException {
        boolean success = false;
        Connection conn  = null;
        PreparedStatement ps = null;
        
        String query = "DELETE FROM clientprdform WHERE productID = ? ";
        try {
            conn = ConnectionPool.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
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
