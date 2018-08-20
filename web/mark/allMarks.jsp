<%-- 
    Document   : allMark
    Created on : Jul 2, 2018, 2:56:37 PM
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
        <h1>List of Marks</h1>
        <form action="MarkController">
            <input type="text" name="txtSearch" pattern="^\d+$" title="digit only">
            <input type="submit" name="action" value="Search">
        </form>
        <p style="color: red"></p>
        
        <c:if test="${requestScope.LIST != null}">
            <c:if test="${not empty requestScope.LIST}" var="isEmpty">
                <table>
                    
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Date created</th>
                        <th>Status</th>
                        <th>Height</th>
                        <th>Weight</th>
                        <th colspan="2">Action</th>
                    </tr>
                    <c:forEach var="mark" items="${requestScope.LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>Mark ${mark.id}</td>
                            <td><img src="mark/img/${mark.id}" alt="avatar" width="100" height="100"></td>
                            <td>${mark.dateCreated}</td>
                            <td>${mark.status}</td>
                            <td>${mark.height}</td>
                            <td>${mark.weight}</td>
                            <c:if test="${sessionScope.ROLE =='admin'}">
                                <td><a href="MarkController?action=Edit&id=${mark.id}">View/Edit</a></td>
                                <td><a href="MarkController?action=Delete&id=${mark.id}">Delete</a></td>
                            </c:if>
                            <c:if test="${sessionScope.NAME == 'tony' && mark.status == 'ready'}">
                                <td><a href="MarkController?action=Update&id=${mark.id}&status=using">Add</a></td>
                            </c:if>
                            <c:if test="${sessionScope.NAME == 'tony' && mark.status == 'using'}">
                                <td><a href="MarkController?action=Update&id=${mark.id}&status=ready">Done</a></td>
                            </c:if>

                        </tr>
                        

                    </c:forEach>
                        <c:if test="${sessionScope.ROLE =='admin'}">
                            <tr>
                                <td colspan="8">

                                    <a style="text-decoration: none" href="/Jarvis/mark/insertMark.jsp">Insert</a>
                                </td>
                            </tr>
                        </c:if>


                </table>
            </c:if>
            <c:if test="${!isEmpty}">
                <h3 style="color: red">No mark found</h3>
            </c:if>
        </c:if>
        <script>
            
        </script>

    </body>
</html>
