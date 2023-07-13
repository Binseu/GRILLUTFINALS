/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ph.pup.itech.grillut.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.pup.itech.grillut.dao.PrdFormDB;
import ph.pup.itech.grillut.model.ProductModel;
import ph.pup.itech.grillut.dao.ProductDao;
import ph.pup.itech.grillut.dao.ProductSRCClass;
import ph.pup.itech.grillut.dao.ProductUpdate;

public class addProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        showDB(request, response);

        String action = request.getServletPath();
        switch (action) {
            case "/products/add": {
                try {
                    getProduct(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(addProduct.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "/products/edit": {
                try {
                    updateProduct(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "/products/edit/submit": {
                try {
                    editProduct(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            
            case "/products/delete": {
                try {
                    deleteProduct(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            default:
                showForm(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void getProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        if (request.getParameter("productID") == null) {
            showForm(request, response);
        }

        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productSize = request.getParameter("productSize");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));

        ProductModel prd = new ProductModel(
                productID, productName, productDescription, productSize, productPrice, productQuantity);
        ProductDao productDao = new ProductDao();
        ProductModel getProduct = productDao.getProductDetails(prd);

        PrdFormDB reg = new PrdFormDB();
        boolean PrdFormDB = reg.createPrdForm(productID, productName, productDescription, productSize, productPrice, productQuantity);

        if (PrdFormDB) {
            String message = getProduct.getproductName() + " with " + getProduct.getproductID() + " has been added to inventory.";

            request.setAttribute("product", getProduct);
            request.setAttribute("message", message);

            System.out.println(message);
        } else {
            String message = "Database Query Error";

            request.setAttribute("product", getProduct);
            request.setAttribute("message", message);

            System.out.println(message);

        }

        showDB(request, response);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/products.jsp");
        rd.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/products.jsp");
        rd.forward(request, response);
    }

    private void showDB(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductSRCClass search = new ProductSRCClass();
        ArrayList<ProductModel> allProducts = null;
        try {
            allProducts = search.getallProducts();
        } catch (ClassNotFoundException ex) {
            String message = "Database Query Error";
            System.out.println(message);
        }
        request.setAttribute("allProducts", allProducts);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String id = request.getParameter("productID");
        ProductUpdate edit = new ProductUpdate();
        ArrayList<ProductModel> productDetails = edit.getProductDetails(id);
        request.setAttribute("productDetails", productDetails);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/productedit.jsp");
        rd.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        if (request.getParameter("productID") == null) {
            showForm(request, response);
        }

        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productSize = request.getParameter("productSize");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        int product_ID = Integer.parseInt(request.getParameter("productID"));

        ProductUpdate edit = new ProductUpdate();
        boolean editUser = edit.editProduct(productID, productName, productDescription, productSize, productPrice, productQuantity, product_ID);

        if (editUser) {
            String message = "User update for " + product_ID + " is successful!";
            request.setAttribute("message", message);
        }

        showDB(request, response);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/products.jsp");
        rd.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        int productID = Integer.parseInt(request.getParameter("productID"));

        ProductUpdate edit = new ProductUpdate();
        boolean editProduct = edit.deleteProduct(productID);

        if (editProduct) {
            String message = productID + " is deleted successfully!";
            request.setAttribute("message", message);
        }

        showDB(request, response);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/products.jsp");
        rd.forward(request, response);
    }
}
