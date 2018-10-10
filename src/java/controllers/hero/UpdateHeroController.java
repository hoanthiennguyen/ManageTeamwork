/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.hero;

import daos.HeroDAO;
import dtos.Hero;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author USER
 */
public class UpdateHeroController extends HttpServlet {

    private static final String ADMIN_SUCCESS = "SearchHeroController";
    private static final String USER_SUCCESS = "home.jsp";
    private static final String HACKED = "index.jsp";

    private static final String ERROR = "home.jsp";

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
        HttpSession session = request.getSession();
        String roleSession = (String) session.getAttribute("ROLE");
        String usernameSession = (String) session.getAttribute("NAME");


        HeroDAO dao = new HeroDAO();
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(new ServletRequestContext(request));

                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String filePath = getServletContext().getRealPath("/") + "\\hero\\img\\";
                FileItem savedItem = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        savedItem = item;
                    }
                }

                String username = (String) params.get("txtUsername");
                String fullname = (String) params.get("txtFullname");
                String role = (String) params.get("txtRole");
                String email = (String) params.get("txtEmail");
                String password = "";
                Hero hero = new Hero(username, password, fullname, email, role);
                //authenticate
                if(!username.equals(usernameSession)&&!roleSession.equals("admin"))
                {
                    
                    url = HACKED;
                    return;
                }
                if (savedItem != null && savedItem.getSize()>0) {
                    savedItem.write(new File(filePath + username));
                }

                if (dao.update(hero)) {
                    if (usernameSession.equals(username)) {
                        url = USER_SUCCESS;
                    } else {
                        url = ADMIN_SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", "Internal error: cannot update");
                }

            }
            
        } catch (Exception e) {
            log("error at UpdateHeroController", e);
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
//    String fullname = request.getParameter("txtFullname");
//            String role = request.getParameter("txtRole");
//            String email = request.getParameter("txtEmail");
//            Hero hero = new Hero(username, password, fullname, email, role);
}
