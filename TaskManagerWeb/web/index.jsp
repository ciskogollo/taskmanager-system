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
        <title>Task Manager</title>
    </head>
    <body>
        <h1>Hola putos! xd</h1>
        <div id="output"></div>
        <input type="text" id="txtinput" name="text"><br>
         <button type="button" id="btnsend">Enviar!</button> 
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
        <script type="text/javascript" src="js/websocket.js"></script>
    </body>
</html>
