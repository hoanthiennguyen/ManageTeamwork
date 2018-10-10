/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.hero;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class Filter {
    public static boolean valid(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("ROLE");
        if(!"admin".equals(role))
        {
            response.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }
}
