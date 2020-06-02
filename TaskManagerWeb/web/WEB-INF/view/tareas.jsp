<%-- 
    Document   : tareas
    Created on : 29-05-2020, 1:28:10
    Author     : cisko
--%>
    <!-- <head> -->
        <title>JSP Page</title>
    </head>
    <body>
        <table class="">
            <thead>
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
                <tr>
                    <th scope="row"></th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
        <c:forEach items="${listTareas}" var="tarea">
            <c:out value="${tarea}"/><br>
        </c:forEach>
    </body>
</html>