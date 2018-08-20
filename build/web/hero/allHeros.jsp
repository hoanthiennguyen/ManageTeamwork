<%-- 
    Document   : allHeros
    Created on : Jun 24, 2018, 8:42:28 PM
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
        <h1>Heros</h1>
        <c:if test="${requestScope.LIST != null}">
            <c:if test="${not empty requestScope.LIST}" var="notEmpty" >
                <table>
                    <tr>
                        <th>No</th>
                        <th>Username</th>
                        <th>Avatar</th>
                        <th>Fullname</th>
                        <th>Role</th>
                        <th colspan="2">Action</th>
                    </tr>
                    <c:forEach var="hero" items="${requestScope.LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${hero.username}</td>
                            <td><img src="hero/img/${hero.username}" alt="avatar" width="100" height="100"></td>
                            <td>${hero.fullname}</td>
                            <td>${hero.role}</td>
                            <td><a href="HeroController?action=Edit&txtUsername=${hero.username}">Edit</a></td>
                            <td><a href="HeroController?action=Delete&txtUsername=${hero.username}">Delete</a></td>
                            
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="7"><a href="hero/insertHero.jsp">Insert a new hero</a><br></td>
                        </tr>
                </table>
                    
            </c:if>
            
                
        </c:if>
        
        
    </body>
</html>
