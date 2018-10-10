/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.missiondetail;

import daos.HeroDAO;
import daos.MissionDetailDAO;
import dtos.MissionDetail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class InsertMissionDetailController extends HttpServlet {

    private static final String SUCCESS = "SearchMissionDetailController";
    private static final String ERROR = "SearchMissionDetailController";
    private static final String INVALID = "details/insertDetail.jsp";

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
        
        //create destination and DAO to execute business logic
        String url = ERROR;
        HeroDAO heroDAO = new HeroDAO();
        MissionDetailDAO dao = new MissionDetailDAO();
        boolean valid = true;
        String err ="";
        String missionName = request.getParameter("missionName");
        //heroName need to be validated
        String heroName = request.getParameter("txtHeroName");
        String subtask = request.getParameter("txtSubtask");
        String status = request.getParameter("txtStatus");
        MissionDetail details = new MissionDetail(heroName, missionName, subtask, status);
        try {
            if(heroDAO.getHero(heroName) == null)
            {
                err = "username does not exist";
                valid = false;
            }
            if(dao.isDedicated(missionName, heroName, subtask))
            {
                err ="this hero is already dedicated this subtask";
                valid = false;
            }
            if(valid)
            {
                if(dao.insert(details)) 
                    url = SUCCESS;
                else 
                    request.setAttribute("ERROR", "internal error: failed to insert");
            }
            else
            {
                request.setAttribute("INVALID", err);
                request.setAttribute("DETAIL", details);
                url = INVALID;
            }
        }
        
        catch (Exception e) {
            log("error at InsertMissionDetailController", e);
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
