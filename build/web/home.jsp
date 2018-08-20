<%-- 
    Document   : home
    Created on : Jun 24, 2018, 8:36:14 AM
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
        <%@include file="header.jsp" %>
        
        <h1>Welcome ${sessionScope.NAME}</h1>
        <p style="color: red">${requestScope.ERROR}</p>
        
        <img src="hero/img/${sessionScope.NAME}" alt="your avatar" width="350" height="350" style="opacity: 1"/>
<!--        <form action="ProcessUpload" id="UploadForm" method="POST" enctype="multipart/form-data">
            <input type="file" name="file" id="myfile" required>
            <input type="submit" value="Change Avatar">
            
        </form>-->
        
        
        
    
   
</body>
</html>
