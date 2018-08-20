<%-- 
    Document   : allMission
    Created on : Jun 29, 2018, 8:58:58 PM
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
        
        <h1>Mission</h1>
        <form action="MissionController" method="POST">
            <input type="text" name="search">
            <input type="submit" name="action" value="Search">
        </form>
        <p style="color: red">${requestScope.ERROR}</p>
        <c:if test="${requestScope.LIST != null}">
            <c:if test="${not empty requestScope.LIST}" var="hasData">
                <table border="1">
                    <tr>
                        <th>No</th>
                        <th>Mission</th>
                        <th>Place</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Status</th>
                        <th colspan="3">Action</th>
                    </tr>
                    <c:forEach var="mission" items="${requestScope.LIST}" varStatus ="counter">
                        <tr style="height: 50px">
                            <td>${counter.count}</td>
                            <td>${mission.missionName}</td>
                            <td>${mission.place}</td>
                            <td class="date">${mission.fromDate}</td>
                            <td class="date">${mission.toDate}</td>
                            <td>${mission.status}</td>
                            <td><a href="MissionDetailController?action=Search&missionName=${mission.missionName}">Subtasks</a></td>
                            <c:if test="${sessionScope.ROLE =='admin'}">
                                <td><a href="MissionController?action=Delete&missionName=${mission.missionName}&search=${param.search}">Delete</a></td>
                            </c:if> 
                            
                            <c:if test="${sessionScope.ROLE == 'admin'}">
                                <td><a href="MissionController?action=Edit&missionName=${mission.missionName}&search=${param.search}">Edit</a></td>
                            </c:if>

                        </tr>

                    </c:forEach>
                        
                </table>
            </c:if>
            <c:if test="${!hasData}">
                <h3 style="color: red">No result found</h3>
            </c:if>

        </c:if>
        <c:if test="${sessionScope.ROLE =='admin'}">
            <table style="margin-top: 0">
                <tr>
                    <td><a href="ListHero">Insert Mission</a><br></td>
                </tr>
            </table>
            
        </c:if>
       
    </body>
</html>
