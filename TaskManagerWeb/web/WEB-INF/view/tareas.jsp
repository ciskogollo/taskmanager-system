<%-- 
    Document   : tareas
    Created on : 29-05-2020, 1:28:10
    Author     : cisko
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row my-3">
    <div class="col-12">
        <a class="btn btn-outline-primary float-right" href="add-tarea">Agregar</a>
        <h1>Mis Tareas</h1>
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
                        <td><fmt:formatDate value="${tarea.fechaIngreso}" pattern="EEEE dd, MMMM yyyy" /></td>
                        <td><fmt:formatDate value="${tarea.fechaPlazo}" pattern="EEEE dd, MMMM yyyy" /></td>
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
                                <a class="btn btn-outline-warning btn-sm m-1" href="confirmar-tarea?id=${tarea.idTarea}" role="button">Terminar</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty listTareas}">
            <div class="text-center">
                <span class="mx-auto">Usted no tiene tareas asignadas.</span>
            </div>
        </c:if>
    </div>
</div>
