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
import ph.pup.itech.grillut.dao.UserDao;
import ph.pup.itech.grillut.dao.UserUpdate;
import ph.pup.itech.grillut.dao.UserFormDB;
import ph.pup.itech.grillut.dao.UserSRCClass;
import ph.pup.itech.grillut.model.UserModel;

public class UserPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        showDB(request, response);

        String action = request.getServletPath();
        switch (action) {
            case "/user/add": {
                try {
                    getUser(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "/user/edit": {
                try {
                    updateUser(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "/user/delete": {
                try {
                    deleteUser(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "/user/edit/submit": {
                try {
                    editUser(request, response);
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

    private void getUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String userID = request.getParameter("userID");
        String userFirstName = request.getParameter("userFN");
        String userMiddleName = request.getParameter("userMN");
        String userLastName = request.getParameter("userLN");
        String userRole = request.getParameter("userRole");

        UserModel user = new UserModel(
                userID, userFirstName, userMiddleName, userLastName, userRole);
        UserDao userDao = new UserDao();
        UserModel getUser = userDao.getUserDetails(user);

        UserFormDB reg = new UserFormDB();
        boolean UserFormDB = reg.UserForm(userID, userFirstName, userMiddleName, userLastName, userRole);

        if (UserFormDB) {
            String message = getUser.getuserID() + " user has been added.";

            request.setAttribute("user", getUser);
            request.setAttribute("message", message);

            System.out.println(message);
        }

        showDB(request, response);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/usermng.jsp");
        rd.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/usermng.jsp");
        rd.forward(request, response);
    }

    private void showDB(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserSRCClass search = new UserSRCClass();
        ArrayList<UserModel> allUsers = null;
        try {
            allUsers = search.getallUser();
        } catch (ClassNotFoundException ex) {
//            String message = "Database Query Error";
//            System.out.println(message);
        }
        request.setAttribute("allUsers", allUsers);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String id = request.getParameter("userID");
        UserUpdate edit = new UserUpdate();
        ArrayList<UserModel> userDetails = edit.getUserDetails(id);
        request.setAttribute("userDetails", userDetails);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/useredit.jsp");
        rd.forward(request, response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String userID = request.getParameter("userID");
        String userFirstName = request.getParameter("userFN");
        String userMiddleName = request.getParameter("userMN");
        String userLastName = request.getParameter("userLN");
        String userRole = request.getParameter("userRole");
        String user_ID = request.getParameter("user_ID");

        System.out.println(userRole);

        UserUpdate edit = new UserUpdate();
        boolean editUser = edit.editUser(userID, userFirstName, userMiddleName, userLastName, userRole, user_ID);

        if (editUser) {
            String message = "User update for " + user_ID + " is successful!";
            request.setAttribute("message", message);
        }
        
        showDB(request, response);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/usermng.jsp");
        rd.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        String userID = request.getParameter("userID");

        UserUpdate edit = new UserUpdate();
        boolean editUser = edit.deleteUser(userID);

        if (editUser) {
            String message = userID + " is deleted successfully!";
            request.setAttribute("message", message);
        }

        showDB(request, response);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/usermng.jsp");
        rd.forward(request, response);
    }
}
