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
                            <td><c:out value="${tarea.descripcion}"/></td>
                            <td><c:out value="${tarea.fechaIngreso}"/></td>
                            <td><c:out value="${tarea.fechaPlazo}"/></td>
                            <td><c:out value="${tarea.idAntes}"/></td>
                            <td><c:out value="${tarea.funcionIdFuncion.idFuncion}"/></td>
                            <td><c:out value="${tarea.statusWorkIdStatus.tipoStatus}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
            