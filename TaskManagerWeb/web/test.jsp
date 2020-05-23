<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Index
    Created on : 19-04-2020, 22:48:10
    Author     : cisko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task Manager Tester</title>
    </head>
    <body>
        <h1>Hola putos! xd</h1>
        <div id="output"></div>
        <input type="text" id="txtinput" name="text"><br>
        <button type="button" id="btnsend">Enviar!</button> 
        <hr>
        <input id="boxtext" name="BOXER!">
        <hr>
        <table>
            <tr>
                <td style="width:100px">Ticker</td>
                <td style="text-align:center">Price</td>
                <td id="price" style="font-size:24pt;font-weight:bold;">--.--</td>
            </tr>
            <tr>
                <td style="font-size:18pt;font-weight:bold;width:100px">DKEJ</td>
                <td style="text-align:center">Volume</td>
                <td id="volume" align="right">--</td>
            </tr>
        </table>
        
        <table border="1">
            <!-- column headers -->
            <tr>
                <c:forEach items="${usuario}" var="user" varStatus="userSt">
                    <th><c:out value="${user}"/></th>
                </c:forEach>
            </tr>
            <!-- column data -->
        </table>
        <p>Usuarios: <c:out value="${validaruser}"/></p>
        
        <script type="text/javascript" src="js/websocket.js"></script>
    </body>
</html>
