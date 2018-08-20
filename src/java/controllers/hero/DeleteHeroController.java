/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.hero;

import daos.HeroDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class DeleteHeroController extends HttpServlet {

    private static final String USER_SUCCESS = "index.jsp";
    private static final String ADMIN_SUCCESS = "SearchHeroController";
    private static final String ERROR = "home.jsp";
    private static final String INVALID = "hero/deleteHero.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            HeroDAO dao = new HeroDAO();
            boolean isAdmin = session.getAttribute("ROLE").equals("admin");
            if (isAdmin) 
            {
                if (dao.delete(username)) {
                    url = ADMIN_SUCCESS;
                } else {
                    request.setAttribute("ERROR", "failed to delete");
                }
            } else 
            {
                if (!dao.login(username, password).equals("failed")) {
                    if (dao.delete(username)) {
                        url = USER_SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "failed to delete");
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", "invalid password");
                }
            }

        } catch (Exception e) {
            log("error at DeleteController", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
