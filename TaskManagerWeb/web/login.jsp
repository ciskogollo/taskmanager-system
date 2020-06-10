<%-- 
    Document   : login
    Created on : 16-05-2020, 21:12:03
    Author     : cisko
--%>


<c:choose>
    <c:when test="${empty objUser}">
        <div class="row">
            <div class="col">
                <form action="login" class="form-signin" method="POST">
                    <img class="mb-4" src="${initParam.images}TMS_login.jpeg" alt="" width="72" height="72">
                    <h1 class="h3 mb-3 font-weight-normal">Acceder</h1>
                    <label for="inputEmail" class="sr-only">Nombre de usuario</label>
                    <input type="text" name="inputEmail" id="inputEmail" class="form-control" placeholder="Nombre de usuario" required autofocus>
                    <label for="inputPassword" class="sr-only">Clave</label>
                    <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Clave" required>
                    <div class="checkbox mb-3">
                      <label>
                        <input type="checkbox" value="remember-me"> Remember me
                      </label>
                    </div>
                    <button id="btnsignuser" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div id="output"></div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <c:redirect url="/index.jsp"/>
    </c:otherwise>
</c:choose>
        
<!-- <script type="text/javascript" src="js/websocket.js"></script> -->
</body>
</html>
