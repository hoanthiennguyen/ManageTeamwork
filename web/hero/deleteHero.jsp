<%-- 
    Document   : deleteHero
    Created on : Jun 24, 2018, 7:30:56 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <h1>Delete your account</h1>
        <form action="/Jarvis/HeroController" method="POST">
            <table>
                <tr>
                    <td>Re-type your password</td>
                    <td><input type="password" name="txtPassword"></td>
                    <td><span style="color: red">${requestScope.INVALID}</span></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="text-align: right">
                        
                        <input type="submit" name="action" value="Delete"></td>
                </tr>
            </table>
            
            
            <input type="hidden" name="txtUsername" value="${sessionScope.NAME}">
            <br>
            
            
        </form>
    </body>
</html>
