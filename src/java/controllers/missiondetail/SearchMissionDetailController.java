/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.missiondetail;

import daos.MissionDAO;
import daos.MissionDetailDAO;
import dtos.MissionDetail;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class SearchMissionDetailController extends HttpServlet {
    private static final String SUCCESS ="details/missionDetail.jsp";
    private static final String HACKED ="index.jsp";
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
        //authenticate
        String url = HACKED;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("ROLE");
        String heroName = (String) session.getAttribute("NAME");
        
        MissionDetailDAO dao = new MissionDetailDAO();
        MissionDAO missionDAO = new MissionDAO();
        String missionName = request.getParameter("missionName");
        try {
            List<MissionDetail> list = dao.getAllMissionDetails(missionName);
            //authenticate
            List<String> members = new ArrayList<>();
            for(MissionDetail detail: list)
            {
                members.add(detail.getHeroName());
            }
            
            if(role.equals("admin") ||members.contains(heroName))
            {
                url = SUCCESS;
                //is the mission editable
                Date toDate = missionDAO.getMission(missionName).getToDate();
                long milis = System.currentTimeMillis();
                Date current = new Date(milis);
                if(toDate.before(current)) 
                    request.setAttribute("EDITABLE", false);
                else 
                    request.setAttribute("EDITABLE", true);
                
                request.setAttribute("LIST", list);
            }
            
        } catch (Exception e) {
            log("error at SearchMissionController",e);
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
