<%-- 
    Document   : editHero
    Created on : Jun 24, 2018, 10:03:32 AM
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
        <h1>Profile</h1>
        <form action="/Jarvis/UpdateHeroController" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                    <td colspan="2" style="text-align: center"><img src="hero/img/${requestScope.HERO.username}" alt="avatar" height="200" width="200"></td>
                </tr>
                <c:if test="${sessionScope.NAME == requestScope.HERO.username}" var="isOwner">
                    <tr>
                        <td>Avatar</td>
                        <td><input accept="image/*" type="file" name="file"></td>
                    </tr>
                </c:if>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="txtUsername" value="${requestScope.HERO.username}" readonly="readonly"></td>                   
                </tr>
                <tr>
                    <td>Fullname</td>
                    <td>
                        <c:if test="${sessionScope.NAME == requestScope.HERO.username}" var="isOwner">
                            <input type="text" name="txtFullname" value="${requestScope.HERO.fullname}" >
                        </c:if>
                        <c:if test="${!isOwner}">
                            <input type="text" name="txtFullname" value="${requestScope.HERO.fullname}" readonly>
                        </c:if>

                    </td>                   
                </tr>
                <tr>
                    <td>Email</td>
                    <td>
                        <c:if test="${isOwner}">
                            <input type="text" name="txtEmail" value="${requestScope.HERO.email}" >
                        </c:if>
                        <c:if test="${!isOwner}">
                            <input type="text" name="txtEmail" value="${requestScope.HERO.email}" readonly>
                        </c:if>
                    </td>                   
                </tr>
                <tr>
                    <td>Role</td>


                    <c:if test="${sessionScope.ROLE == 'admin'}" var="isAdmin">
                        <td>
                            <select name="txtRole">
                                <option value="user">user</option>
                                <c:if test="${requestScope.HERO.role=='admin'}" var="ck">
                                    <option value="admin" selected>admin</option>
                                </c:if>
                                <c:if test="${!ck}">
                                    <option value="admin">admin</option>
                                </c:if>

                            </select>
                        </td>

                    </c:if>
                    <c:if test="${!isAdmin}">
                        <td><input type="text" name="txtRole" value="${requestScope.HERO.role}" readonly="readonly"></td>                 

                    </c:if>
                </tr>
                <c:if test="${isAdmin || isOwner}">
                    <tr>
                        <td></td>
                        <td style="text-align: left">
                            <input  type="submit" name="action" value="Update">
                        </td>               
                    </tr> 
                </c:if>

            </table>

        </form>

        <c:if test="${sessionScope.NAME == requestScope.HERO.username}">
            <form>
                <table>
                    <tr>
                        <td  style="text-align: center"><a href="hero/changePassword.jsp">Change password</a></td>
                    </tr>
                </table>
            </form>

        </c:if>


    </body>
</html>
