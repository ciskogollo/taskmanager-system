<%-- 
    Document   : tareas
    Created on : 29-05-2020, 1:28:10
    Author     : cisko
--%>
<div class="row">
    <div class="col-12">
        <div class="btn-group" role="group" aria-label="Opciones">
            <a href="add-tarea" class="btn btn-secondary">Agregar</a>
        </div>
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
                    <th scope="col">Antescesora</th>
                    <th scope="col">Función</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listTareas}" var="tarea">
                    <tr>
                        <th scope="row"><c:out value="${tarea.idTarea}"/></th>
                        <td><a href="ver-tarea?id=${tarea.idTarea}">${tarea.descripcion}</a></td>
                        <td>${tarea.fechaIngreso}</td>
                        <td>${tarea.fechaPlazo}</td>
                        <td><a href="ver-tarea?id=${tarea.idAntes}">${tarea.idAntes}</a></td>
                        <td>${tarea.funcionIdFuncion.idFuncion}</td>
                        <td>
                            <c:choose>
                                <c:when test="${tarea.statusWorkIdStatus.idStatus == 1}">
                                    <span class="badge badge-pill badge-primary m-1">${tarea.statusWorkIdStatus.tipoStatus}</span>
                                </c:when>
                                <c:when test="${tarea.statusWorkIdStatus.idStatus == 2}">
                                    <span class="badge badge-pill badge-secondary m-1">${tarea.statusWorkIdStatus.tipoStatus}</span>
                                </c:when>
                                <c:when test="${tarea.statusWorkIdStatus.idStatus == 3}">
                                    <span class="badge badge-pill badge-danger m-1">${tarea.statusWorkIdStatus.tipoStatus}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-pill badge-info m-1">${tarea.statusWorkIdStatus.tipoStatus}</span>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${tarea.statusWorkIdStatus.tipoStatus == 'Activo'}">
                                <a class="btn btn-outline-success m-1" href="confirmar-tarea?id=${tarea.idTarea}" role="button">Ok</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
            