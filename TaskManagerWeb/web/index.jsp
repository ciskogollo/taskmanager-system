<%-- 
    Document   : index
    Created on : xx-08-2020, xx:xx::xx
    Author     : cisko
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
${pageContext.servletContext.setAttribute("Titulo","Dashboard")}

<c:choose>
    <c:when test="${empty objUser}">
        <p>No logueado:c</p>
        <c:redirect url="/login.jsp"/>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-12">
                <p class="text-right">Bienvenid@ <b><c:out value="${objUser.nombre}"/></b></p>
                <p>Logueado de pana.</p>
                <h1>Dashboard</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Ingreso</th>
                            <th scope="col">Plazo</th>
                            <th scope="col">Status</th>
                            <th scope="col">Cumplimiento</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listTareas}" var="tarea">
                            <tr>
                                <th scope="row"><c:out value="${tarea.idTarea}"/></th>
                                <td><a href="ver-tarea?id=${tarea.idTarea}">${tarea.descripcion}</a></td>
                                <td><fmt:formatDate value="${tarea.fechaIngreso}" pattern="dd-MM-yyyy" /></td>
                                <td><fmt:formatDate value="${tarea.fechaPlazo}" pattern="dd-MM-yyyy" /></td>
                                <td>${tarea.statusWorkIdStatus.tipoStatus}</td>
                                <!--<td><fmt:formatDate value="${tarea.fechaIngreso}" pattern="dd-MM-yyyy" />-<fmt:formatDate value="${tarea.fechaPlazo}" pattern="dd-MM-yyyy" /></td>-->
                                <td id="fechcumplimiento-${tarea.idTarea}">
                                    <!-- ${tarea.fechaPlazo} - ${tarea.fechaIngreso} -->
                                    <script>
                                        var fIng = moment('<fmt:formatDate value="${tarea.fechaIngreso}" pattern="yyyy-MM-dd" />');
                                        var fPla = moment('<fmt:formatDate value="${tarea.fechaPlazo}" pattern="yyyy-MM-dd" />');
                                        var fNow = moment();

                                        var plaTot = fPla.diff(fIng, 'days');
                                        var plaDesAh = fPla.diff(fNow, 'days');
                                        //var plaDesAh = moment(fPla).fromNow(true);
                                        console.log('Plazo desde ahora: ',plaDesAh,' dias');
                                        var porcCumplim = (plaTot-plaDesAh);
                                        if(plaDesAh >= 7){
                                            document.getElementById("fechcumplimiento-${tarea.idTarea}").innerHTML = "<div class='punto-status status-verde mx-auto my-2'></div>";
                                        }else if(plaDesAh < 7 && plaDesAh >= 1 ){
                                            document.getElementById("fechcumplimiento-${tarea.idTarea}").innerHTML = "<div class='punto-status status-amarillo mx-auto my-2'></div>";
                                        }else if(plaDesAh < 1){
                                            document.getElementById("fechcumplimiento-${tarea.idTarea}").innerHTML = "<div class='punto-status status-rojo mx-auto my-2'></div>";
                                        }
                                    </script>
                                </td>
                            </tr><c
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</div>
<script>
    ctx.arc(360,70,50,0,(Math.PI/180)*360,true);
    ctx.strokeStyle = "#f00";
    ctx.lineWidth = 10;
    ctx.stroke();
</script>