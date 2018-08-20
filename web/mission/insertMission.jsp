<%-- 
    Document   : insertMission
    Created on : Jun 30, 2018, 6:31:39 AM
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
        <h1>Insert Mission!</h1>
        <p id="error" style="color: red"></p>
        <p style="color: red">${requestScope.INVALID}</p>
        <form action="/Jarvis/MissionController" method="POST" onsubmit="return checkValid();">
            <table>

                <tr>
                    <td>Mission name</td>
                    <td><input type="text" name="txtMissionName" required></td>
                </tr>
                <tr>
                    <td>Place</td>
                    <td><input type="text" name="txtPlace" value="${requestScope.MISSION.place}" required></td>
                </tr>
                <tr>
                    <td>From </td>
                    <td><input type="date" id="day1" name="txtFromDate" value="${requestScope.MISSION.fromDate}" required></td>
                </tr>
                <tr>
                    <td>To</td>
                    <td><input type="date" id="day2" name="txtToDate" value="${requestScope.MISSION.toDate}" required></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="txtStatus" >
                            <option value="To do" > To do </option>
                            
                            
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="font-weight: bold">Subtask</td>
                </tr>



                <c:forEach items="${requestScope.LIST}" var="username">
                    <tr>
                        <td style="text-align: right">${username}</td>
                        <td><input type="text" name="${username}"></td>
                    </tr>


                </c:forEach>




                <tr>
                    
                    <td colspan="3" style="text-align: center">

                        <input type="submit" name="action" value="Insert">
                    </td>
                </tr>
            </table>
        </form>
        <script>
            function checkValid()
            {
                let first = document.getElementById("day1").value.split("-");
                let second = document.getElementById("day2").value.split("-");
                let d1 = new Date(first[0], first[1] - 1, first[2]);
                let d2 = new Date(second[0], second[1] - 1, second[2]);
                let current = new Date();
                if (d1 > d2 || d1 < current)
                {
                    document.getElementById("error").innerHTML = "invalid day";
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
