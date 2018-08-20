<%-- 
    Document   : missionDetail
    Created on : Jun 29, 2018, 9:37:33 PM
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
        <h1>${param.missionName}'s Details</h1>
        <p style="color: red">${requestScope.ERROR}</p>
        <c:if test="${requestScope.LIST != null}">
            <c:if test="${not empty requestScope.LIST}">

                <table border="1">
                    <tr>
                        <th>No</th>

                        <th>Hero Name</th>
                        <th>Avatar</th>
                        <th>Subtask</th>

                        <th>Status</th>
                        <th colspan="2">Action</th>
                    </tr>
                    <c:forEach var="detail" items="${requestScope.LIST}" varStatus="counter">
                        <tr>    
                            <td>${counter.count}</td>

                            <td><a href="EditHeroController?txtUsername=${detail.heroName}">${detail.heroName}</a></td>
                            <td><img src="hero/img/${detail.heroName}" alt="avatar" width="100" height="100"></td>
                            <td>${detail.subtask}</td>
                            <td>${detail.status}</td>
                            <c:if test="${sessionScope.NAME == detail.heroName && requestScope.EDITABLE =='true'}" var="editable">
                                <td><a href="details/editDetail.jsp?missionName=${detail.missionName}&heroName=${detail.heroName}&subtask=${detail.subtask}&status=${detail.status}">Edit</a></td>
                            </c:if>

                            <c:if test="${sessionScope.ROLE =='admin'}">
                                <td><a href="MissionDetailController?action=Delete&missionName=${detail.missionName}&heroName=${detail.heroName}&subtask=${detail.subtask}">Delete</a></td>
                            </c:if>

                        <tr>
                        </c:forEach>
                </table>

            </c:if>

        </c:if>
        <c:if test="${requestScope.EDITABLE == 'true' && sessionScope.ROLE =='admin'}">
            <table>
                <tr>
                    <td><a href="details/insertDetail.jsp?missionName=${param.missionName}">Add a hero</a>
                    </td>
                </tr>
            </table>
           
        </c:if>


    </body>
</html>
