/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mark;

import daos.MarkDAO;
import dtos.Mark;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author USER
 */
public class UpdateMarkController extends HttpServlet {

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
        
        MarkDAO dao = new MarkDAO();
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            //request is multipart, that means javis is using web
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(new ServletRequestContext(request));

                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String filePath = getServletContext().getRealPath("/") + "\\mark\\img\\";
                FileItem savedItem = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        savedItem = item;
                    }
                }

                int id = Integer.parseInt((String) params.get("id"));
                String status = (String) params.get("status");

                if (savedItem != null && savedItem.getSize()>0) {
                    savedItem.write(new File(filePath + id));
                }
                int height = Integer.parseInt((String) params.get("height"));
                int weight = Integer.parseInt((String) params.get("weight"));
                Date dateCreated = Date.valueOf((String) params.get("dateCreated"));
                Mark mark = new Mark(id, dateCreated, status, height, weight);

                if (!dao.update(mark)) {
                    request.setAttribute("ERROR", "internal error: failed to update");
                }

            } //request is not multipart, that means tony is using web
            else {
                int id = Integer.parseInt(request.getParameter("id"));
                String status = request.getParameter("status");
                dao.update(id, status);
            }
        } catch (Exception e) {
            log("error at UpdateMarkController", e);
        } finally {
            request.getRequestDispatcher("SearchMarkController").forward(request, response);
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
