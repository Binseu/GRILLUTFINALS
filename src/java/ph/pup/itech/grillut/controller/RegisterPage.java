/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ph.pup.itech.grillut.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.pup.itech.grillut.dao.RegisterDB;
import ph.pup.itech.grillut.dao.RegisterDao;
import ph.pup.itech.grillut.model.RegisterModel;

public class RegisterPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/customer/registration/add": {
                try {
                    registerUser(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, ex);
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

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        
        String registerFirstName = request.getParameter("registerFirstName");
        String registerMiddleName = request.getParameter("registerMiddleName");
        String registerLastName = request.getParameter("registerLast");
        String registerUsername = request.getParameter("registerUsername");
        String registerPassword = request.getParameter("registerPassword");
        String registerAddress = request.getParameter("registerAddress");
        String registerBirthday = request.getParameter("registerBirthday");
        String registerNumber = request.getParameter("registerNumber");

        RegisterModel customer = new RegisterModel(
                registerFirstName, registerMiddleName, registerLastName, registerUsername, registerPassword, registerAddress, registerBirthday, registerNumber);
        RegisterDao registerDao = new RegisterDao();
        RegisterModel registered = registerDao.getRegisterDetails(customer);

        RegisterDB reg = new RegisterDB();
        boolean RegisterDB = reg.RegisterForm(registerFirstName, registerMiddleName, registerLastName, registerUsername, registerPassword, registerAddress, registerBirthday, registerNumber);

        if (RegisterDB) {
            String message = registered.getregisterUsername() + " customer has been added.";

            request.setAttribute("message", message);
            System.out.println(message);
        } else {
            String message = "Database Query Error";

            request.setAttribute("message", message);

            System.out.println(message);

        }
        
        showDB(request, response);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/register.jsp");
        rd.forward(request, response);
    }

}
