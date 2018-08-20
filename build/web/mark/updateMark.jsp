<%-- 
    Document   : updateMark
    Created on : Jul 2, 2018, 4:09:13 PM
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
        <h1>Update Mark</h1>
        <form action="/Jarvis/UpdateMarkController" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input style="text-align: center" type="text" name="id" value="${requestScope.MARK.id}" readonly></td>

                </tr>
                <tr>
                    <td colspan="3" style="text-align: center"><img src="mark/img/${requestScope.MARK.id}" height="150" width="150" alt="image">
                    </td>
                </tr>
                <tr>
                    <td>Avatar</td>
                    <td><input accept="image/*" type="file" name="file"></td>
                </tr>
                <tr>
                    <td>Date created</td>
                    <td><input type="date" name="dateCreated" value="${requestScope.MARK.dateCreated}"></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="text" name="status" value="${requestScope.MARK.status}" pattern="^(using)|(fixing)|(ready)|(destroyed)$" title="'using' or 'fixing' or 'ready' or 'destroyed'" required></td>
                </tr>
                <tr>
                    <td>Height</td>
                    <td><input type="text" name="height" value="${requestScope.MARK.height}" required="required" pattern="^\d+$"></td>
                    <td>cm</td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td><input type="text" name="weight" value="${requestScope.MARK.weight}" required="required" pattern="^\d+$"></td>
                    <td>kg</td>
                </tr>
                <tr>
                    <td></td>
                    <td  style="text-align: left">

                        <input type="submit" name="action" value="Update">
                    </td>
                </tr>
            </table>




        </form>
        
    </body>
</html>
