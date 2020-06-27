<%-- 
    Document   : ver-tarea
    Created on : 04-06-2020, 17:19:54
    Author     : cisko
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row my-3">
    <div class="col-12">
        <a class="btn btn-outline-dark" href="tareas">Volver</a>
        <h1>Tarea: </h1>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <form action="edit-tarea" method="POST">
            <div class="form-group">
                <label for="txtDescriTarea">Descripción</label>
                <input type="text" class="form-control" id="txtDescriTarea" name="txtDescriTarea" placeholder="Nombre de la Tarea" value="${tareaSeleccionada.descripcion}" disabled>
            </div>
            <div class="form-group">
                <label for="datePlazoTarea">Plazo</label>

                <c:set var="fPlTarSelected" scope="session" value="" />
                <input type="date" class="form-control" id="datePlazoTarea" name="datePlazoTarea" value='<fmt:formatDate value="${tareaSeleccionada.fechaPlazo}" pattern="yyyy-MM-dd" />' disabled>
            </div>
            <div class="form-group">
                <label for="selResponsableTarea">Responsable</label>
                <select multiple class="form-control" id="selResponsableTarea" name="selResponsableTarea" disabled>
                    <c:forEach items="${usuariosRegistrados}" var="userReg">
                        <c:choose>
                            <c:when test="${tareaSeleccionada.usuarioIdUsuario.idUsuario == userReg.idUsuario}">
                                <option value="<c:out value="${userReg.idUsuario}"/>" selected> <c:out value="${userReg.nombre}"/> </option>
                            </c:when>
                            <c:otherwise>
                                <option value="<c:out value="${userReg.idUsuario}"/>"> <c:out value="${userReg.nombre}"/> </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="selTareaAntes">Dependiente de</label>
                <select multiple class="form-control" id="selTareaAntes" name="selTareaAntes" disabled>
                    <c:forEach items="${tareasRegistradas}" var="tareaReg">
                        <c:choose>
                            <c:when test="${tareaSeleccionada.idAntes == tareaReg.idTarea}">
                                <option value="<c:out value="${tareaReg.idTarea}"/>" selected> <c:out value="${tareaReg.descripcion}"/> </option>
                            </c:when>
                            <c:otherwise>
                                <option value="<c:out value="${tareaReg.idTarea}"/>"> <c:out value="${tareaReg.descripcion}"/> </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <small id="emailHelp" class="form-text text-muted">No es obligatoria una tarea antescesora.</small>
            </div>
            <div class="form-group">
                <label for="selStatusWork">Estado</label>
                <select class="form-control" id="selStatusWork" name="selStatusWork" disabled>
                    <c:forEach items="${estadosRegistrados}" var="estadoReg">
                        <c:choose>
                            <c:when test="">
                                
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="checkEdit">
                <label class="form-check-label" for="checkEdit">Editar</label>
            </div>
            <button id="btnUpdate" type="submit" class="btn btn-outline-success" disabled>Actualizar</button>
            <a id="btnDelete" class="btn btn-outline-danger" href="del-tarea?id=${tareaSeleccionada.idTarea}" disabled>Eliminar</a>
        </form>
        <script>
            /*Enabling edition mode xd*/
            var modeEdition = document.getElementById('checkEdit');
            modeEdition.addEventListener('change', validarModEd, false);
            function validarModEd(){
                var checked = modeEdition.checked;
                if(modeEdition.checked){
                    console.log('Modo Edición activado.');
                    $("#txtDescriTarea").prop("disabled", false);
                    $("#datePlazoTarea").prop("disabled", false);
                    $("#selResponsableTarea").prop("disabled", false);
                    $("#selTareaAntes").prop("disabled", false);
                    $("#btnUpdate").prop("disabled", false);
                }else{
                    console.log('Modo Edición desactivado.');
                    $("#txtDescriTarea").prop("disabled", true);
                    $("#datePlazoTarea").prop("disabled", true);
                    $("#selResponsableTarea").prop("disabled", true);
                    $("#selTareaAntes").prop("disabled", true);
                    $("#btnUpdate").prop("disabled", true);
                }
            }
        </script>
    </div>
</div>