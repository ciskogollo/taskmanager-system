<%-- 
    Document   : index
    Created on : xx-08-2020, xx:xx::xx
    Author     : cisko
--%>

${pageContext.servletContext.setAttribute("Titulo","Dashboard")}
<div class="row">
    <div class="col-12">
        <p class="text-right">Bienvenid@ <b><c:out value="${objUser.nombre}"/></b></p>
        <p>Logueado de pana.</p>
        <h1>Home</h1>
        <div id="output"></div>
        <input type="text" id="txtinput" name="text"><br>
        <button type="button" id="btnsend">Enviar!</button> 
        <hr>
        <input id="boxtext" name="BOXER!">
        <hr>
        <c:choose>
            <c:when test="${empty objUser}">
                <p>No logueado:c</p>
                <c:redirect url="/login.jsp"/>
            </c:when>
            <c:otherwise>
                <p>Logueado de pana.</p>
                
                <a href="tareas">-> TAREAS</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
                