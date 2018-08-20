<%-- 
    Document   : editMission
    Created on : Jun 30, 2018, 6:32:16 AM
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
        <h1>Edit Mission!</h1>
        <p id="error" style="color: red"></p>
        <form action="/Jarvis/MissionController" method="POST" onsubmit="return checkValid();">
            <table>

                <tr>
                    <td>Mission name</td>
                    <td><input readonly="readonly" value="${param.missionName}" name="txtMissionName"></td>
                </tr>
                <tr>
                    <td>Place</td>
                    <td><input type="text" name="txtPlace" value="${requestScope.MISSION.place}"></td>
                </tr>
                <tr>
                    <td>From </td>
                    <td><input type="date" id="day1" name="txtFromDate" value="${requestScope.MISSION.fromDate}"></td>
                </tr>
                <tr>
                    <td>To</td>
                    <td><input type="date" id="day2" name="txtToDate" value="${requestScope.MISSION.toDate}"></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>                        
                        <select id="status" name="txtStatus" >
                            <c:forTokens items="To do,Doing,Done,Failed" delims="," var="status">
                                <c:if test="${requestScope.MISSION.status == status}" var="isStatus">
                                    <option value="${status}" selected> ${status} </option>
                                </c:if>
                                <c:if test="${!isStatus}">
                                    <option value="${status}"> ${status} </option>
                                </c:if>
                            </c:forTokens>
                            
                        </select>
                    </td>
                </tr>
                <tr>                    
                    <td style="text-align: center" colspan="2">
                        <input type="hidden" name="search" value="${param.search}">
                        <input type="submit" name="action" value="Update">
                    </td>
                </tr>
            </table>
        </form>
                <script>
            function checkValid()
            {
                let first = document.getElementById("day1").value.split("-");
                let second = document.getElementById("day2").value.split("-");
                let status = document.getElementById("status").value;
                let d1 = new Date(first[0], first[1] - 1, first[2]);
                let d2 = new Date(second[0], second[1] - 1, second[2]);
                let current = new Date();
                if (d1 > d2)
                {
                    document.getElementById("error").innerHTML = "invalid day";
                    return false;
                }
                if(current > d2 && (status === 'To do' || status ==='Doing'))
                {
                    document.getElementById("error").innerHTML = "invalid day or status";
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
