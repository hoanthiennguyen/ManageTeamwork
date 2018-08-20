<%-- 
    Document   : insertDetail
    Created on : Jul 1, 2018, 4:00:46 PM
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
        <h1>Insert member's subtask!</h1>
        <p style="color: red">${INVALID}</p>
        <form action="/Jarvis/MissionDetailController" method="POST">

            <table>
                <tr>
                    <td>Mission</td>
                    <td><input type="text" name="missionName" value="${param.missionName}" readonly></td>
                </tr>
                <tr>
                    <td>Hero</td>
                    <td><input type="text" name="txtHeroName" value="${requestScope.DETAIL.heroName}" required></td>
                </tr>
                <tr>
                    <td>Subtask</td>
                    <td><input type="text" name="txtSubtask" value="${requestScope.DETAIL.subtask}" required></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="txtStatus">
                            <option value="To do">To do</option>
                            
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        
                        <input type="submit" name="action" value="Insert">
                    </td>
                </tr>
            </table>




        </form>
    </body>
</html>
