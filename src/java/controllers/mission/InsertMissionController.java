/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mission;

import daos.HeroDAO;
import daos.MissionDAO;
import daos.MissionDetailDAO;
import dtos.Mission;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class InsertMissionController extends HttpServlet {

    private static final String INVALID = "mission/insertMission.jsp";
    private static final String SUCCESS = "SearchMissionController";
    private static final String ERROR = "mission/allMission.jsp";

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
        
        //create dao and URL destination
        String url  = ERROR;
        Mission mission = null;
        List<String> heros = null;
        List<String> members = new ArrayList<>();
        List<String> subtasks = new ArrayList<>();
        
        HeroDAO heroDAO = new HeroDAO();
        MissionDAO missionDAO = new MissionDAO();
        MissionDetailDAO detailDAO = new MissionDetailDAO();
        try {
            String missionName = request.getParameter("txtMissionName");
            String place = request.getParameter("txtPlace");
            Date fromDate = Date.valueOf(request.getParameter("txtFromDate"));
            Date toDate = Date.valueOf(request.getParameter("txtToDate"));
            String status = request.getParameter("txtStatus");
            mission = new Mission(missionName, place, status, fromDate, toDate);
            //get mission's members and their subtask
            heros = heroDAO.listAllHeroName();
            for(String hero: heros)
            {
                String subtask = request.getParameter(hero);
                if(subtask != null && !subtask.isEmpty())
                {
                    members.add(hero);
                    subtasks.add(subtask);
                }
            }
            //execute update
            if(missionDAO.insert(mission) && detailDAO.insertWholeMissionDetail(missionName, members, subtasks))
                url = SUCCESS;
            else
                request.setAttribute("ERROR", "Internal error: failed to insert");
        }
        catch(SQLException ex)
        {
            if(ex.getMessage().contains("duplicate"))
            {
                url = INVALID;
                request.setAttribute("INVALID", "this mission's name is already used");
                request.setAttribute("MISSION", mission);
                request.setAttribute("LIST", heros);
            }
        }
        catch (Exception e) {
            log("Error at InsertMissionController ", e);
        }
        finally{
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
