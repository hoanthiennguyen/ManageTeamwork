<%-- 
    Document   : navigation
    Created on : Jul 3, 2018, 3:36:09 PM
    Author     : USER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--===============================================================================================-->	
	
        <!--===============================================================================================-->	

	
        <link rel="stylesheet" type="text/css" href="/Jarvis/css/nav.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <nav>
            <a href="/Jarvis/home.jsp" class="menu"> Home </a>
            <a href="/Jarvis/MissionController?action=Search" class="menu"> Missions </a>
            <a href="/Jarvis/HeroController?action=Edit&txtUsername=${sessionScope.NAME}" class="menu"> Profile </a>
            <c:if test="${sessionScope.ROLE == 'admin'}">
                <a href="/Jarvis/HeroController?action=Search" class="menu">Heros</a>
            </c:if>
            <c:if test="${sessionScope.NAME =='tony' || sessionScope.ROLE =='admin'}">
                <a href="/Jarvis/MarkController?action=Search" class="menu"> Marks</a>
            </c:if>
                <a href="/Jarvis/HeroController?action=Logout" class="menu">Log out</a><br>
        </nav>
    </body>
</html>
