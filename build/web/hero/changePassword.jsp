<%-- 
    Document   : changePassword
    Created on : Jun 28, 2018, 7:59:12 PM
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
        <h1>Change password!</h1>
        <form action="/Jarvis/HeroController" method="POST" onsubmit="return checkValid();">
            <p id="error" style="color: red"></p>
            <table>
                <tr>
                    <td>Current password</td>
                    <td><input type="password" name="currentPassword"></td>
                    <td style="color: red">${requestScope.ERROR}</td>
                </tr>
                <tr>
                    <td>New password</td>
                    <td><input type="password" name="newPassword" id="newPassword"></td>
                </tr>
                <tr>
                    <td>Confirm password</td>
                    <td><input type="password" name="retypePassword" id="confirmPassword"></td>
                </tr>
                <tr>
                    
                    <td style="text-align: right" colspan="2">
                    
                    
                    <input type="submit" name="action" value="Change">
                    </td>
                </tr>
                

            </table>
            
        </form>
        <script>
            function checkValid()
            {
                var result = true;
                var msg = "";
                var npass = document.getElementById("newPassword").value;
                var confirmPass = document.getElementById("confirmPassword").value;
                if (npass.length < 6)
                {
                    result = false;
                    msg = "too short password ";
                }
                    
                if(confirmPass !== npass)
                {
                    result = false;
                    msg +=", two field of password don't match";
                }
                document.getElementById("error").innerHTML = msg;
                return result;
            }
        </script>
    </body>
</html>
