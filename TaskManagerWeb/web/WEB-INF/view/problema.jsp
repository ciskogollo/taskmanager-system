<%-- 
    Document   : tareas
    Created on : 29-05-2020, 1:28:10
    Author     : cisko
--%>
<div class="row my-3">
    <div class="col-12">
        <button class="btn btn-outline-primary float-right" data-toggle="modal" data-target="#modalExample">Ver Lista</button>
        <!-- Button trigger modal -->
        <h1>Reportar Problema</h1>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <form id="form-reg-problem" action="problema" method="POST">
            <div class="form-group">
                <label for="txtComentario">Comentario</label>
                <textarea type="text" class="form-control" id="txtComentario" name="txtComentario" placeholder="Descripción del problema..." required></textarea>
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="checkProblem">
                <label class="form-check-label" for="checkProblem">Estoy seguro de registrar</label>
            </div>
            <div class="form-group my-3">
                <button class="btn btn-warning" onclick="confirmar()">Registrar</button>
            </div>
          </form>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="modalExample" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Problemas registrados</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Comentario</th>
                        <th scope="col">Notificador</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${problemasRegistrados}" var="problema">
                        <tr>
                            <th scope="row"><c:out value="${problema.idProblema}"/></th>
                            <td>${problema.comentario}</td>
                            <td>${problema.usuarioIdUsuario.nombre}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
            <!--<button type="button" class="btn btn-primary">Listo</button>-->
        </div>
      </div>
    </div>
</div>
<script>
    function confirmar(){
        var checked = document.getElementById("checkProblem").checked;
        var comment = $('#txtComentario');
        if(comment.val().length !== 0){
            if(checked === true){
                let confirma = confirm('Está a punto de reportar un problema...');
                if(confirma){
                    document.form-reg-problem.submit();
                }
            }
        }else{
            confirm("El campo de comentario está vacío.");
        }
    }
</script>