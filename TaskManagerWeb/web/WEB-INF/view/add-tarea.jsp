<%-- 
    Document   : tareas
    Created on : 29-05-2020, 1:28:10
    Author     : cisko
--%>
    <div class="row my-3">
        <div class="col-12">
            <a class="btn btn-outline-dark" href="tareas">Volver</a>
            <h1>Nueva Tarea</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <form action="add-tarea" method="POST">
                <div class="form-group">
                    <label for="txtDescriTarea">Descripción</label>
                    <input type="text" class="form-control" id="txtDescriTarea" name="txtDescriTarea" placeholder="Nombre de la Tarea">
                </div>
                <div class="form-group">
                    <label for="datePlazoTarea">Plazo</label>
                    <input type="date" class="form-control" id="datePlazoTarea" name="datePlazoTarea">
                </div>
                <div class="form-group">
                    <label for="selResponsableTarea">Responsable</label>
                    <select multiple class="form-control" id="selResponsableTarea" name="selResponsableTarea">
                        <c:forEach items="${usuariosRegistrados}" var="usuarioReg">
                            <option value="<c:out value="${usuarioReg.idUsuario}"/>"><c:out value="${usuarioReg.nombre}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selTareaAntes">Dependiente de</label>
                    <select multiple class="form-control" id="selTareaAntes" name="selTareaAntes">
                        <c:forEach items="${tareasRegistradas}" var="tareaReg">
                            <option><c:out value="${tareaReg.descripcion}"/></option>
                        </c:forEach>
                    </select>
                    <small id="emailHelp" class="form-text text-muted">No es obligatoria una tarea antescesora.</small>
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Estoy seguro de registrar</label>
                </div>
                <button type="submit" class="btn btn-success">Registrar</button>
              </form>
        </div>
    </div>
            