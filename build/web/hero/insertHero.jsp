<%-- 
    Document   : insertHero
    Created on : Jun 26, 2018, 9:09:38 PM
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
        <h1>Insert hero!</h1>
        <h3 style="color: red">${requestScope.ERROR}</h3>
        <form action="/Jarvis/HeroController" method="POST">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="txtUsername" value="${requestScope.HERO.username}" required></td>
                    
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtPassword" required></td>
                    
                </tr>
                <tr>
                    <td>Re-type password</td>
                    <td><input type="password" name="txtRetypePassword" required></td>
                    
                </tr>
                <tr>
                    <td>Full name</td>
                    <td><input type="text" name="txtFullname" value="${requestScope.HERO.fullname}"></td>
                    
                </tr>
                <tr>
                    <td>Role</td>
                    <td>
                        <select name="txtRole">
                            <option value="user">user</option>
                            <option value="admin">admin</option>
                        </select>
                    </td>
                    
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="txtEmail" value="${requestScope.HERO.email}"></td>
                    
                </tr>
                <tr>
                    
                    <td style="text-align: right" colspan="2">
                        
                        <input type="submit" name="action" value="Insert">
                    </td>
                    
                </tr>
            </table>



        </form>
    </body>
</html>
