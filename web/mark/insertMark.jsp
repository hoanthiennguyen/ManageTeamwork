<%-- 
    Document   : insertMark
    Created on : Jul 2, 2018, 4:29:13 PM
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
        <h1>Insert Mark</h1>
        <h3 style="color: red">${requestScope.ERROR}</h3>
        <form action="/Jarvis/MarkController" method="POST">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="${requestScope.MARK.id}"></td>
                    
                </tr>
                <tr>
                    <td>Date created</td>
                    <td><input type="date" name="dateCreated" value="${requestScope.MARK.dateCreated}"></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="text" name="status" value="${requestScope.MARK.status}"  pattern="^(using)|(fixing)|(ready)|(destroyed)$" title="using or fixing or ready or destroyed"></td>
                </tr>
                <tr>
                    <td>Height</td>
                    <td><input type="text" name="height" value="${requestScope.MARK.height}" pattern="^\d+$"></td>
                    <td>cm</td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td><input type="text" name="weight" value="${requestScope.MARK.weight}" pattern="^\d+$"></td>
                    <td>kg</td>
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
