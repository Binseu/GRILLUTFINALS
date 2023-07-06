/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ph.pup.itech.grillut.controller;

/**
 *
 * @author LAPTOP - Binseu
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.pup.itech.grillut.dao.UserSRCClass;
import ph.pup.itech.grillut.model.UserModel;

public class UserSRC extends HttpServlet {
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
      
        UserSRCClass search = new UserSRCClass();
        ArrayList<UserModel> allUsers = search.getallUser();
        request.setAttribute("allUsers", allUsers);
        
        RequestDispatcher rd = request.getRequestDispatcher("/usermng.jsp");
        rd.forward(request, response);
    }
}
