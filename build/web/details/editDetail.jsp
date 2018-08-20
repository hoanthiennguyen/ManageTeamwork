<%-- 
    Document   : editDetail
    Created on : Jul 1, 2018, 3:15:46 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <h1>Edit details</h1>
        <form action="/Jarvis/MissionDetailController" method="POST">
            <input type="hidden" name="heroName" value="${param.heroName}"><br>

            <table>
                <tr>
                    <td>Mission</td>
                    <td><input type="text" name="missionName" value="${param.missionName}"></td>
                </tr>
                <tr>
                    <td>Subtask</td>
                    <td><input name="subtask" value="${param.subtask}" readonly></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="txtStatus">
                            <c:forTokens items="To do,Doing,Done,Failed" delims="," var="stt">
                                <c:if test="${param.status == stt}">
                                    <option value="${stt}" selected>${stt}</option>
                                </c:if>
                                <c:if test="${param.status != stt}">
                                    <option value="${stt}">${stt}</option>
                                </c:if>
                            </c:forTokens>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        
                        <input type="submit" name="action" value="Update">
                    </td>
                </tr>
            </table>



       
    </form>
</body>
</html>
