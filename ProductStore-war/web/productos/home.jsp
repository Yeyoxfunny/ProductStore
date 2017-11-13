<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <title>Productos</title>
</head>
<body>
    <%@include file="../WEB-INF/layouts/navbar.jsp"%>
    <div class="container">
        <h3 style="margin-top: 2rem;">
            <span>Productos</span>
            <button type="button" class="btn btn-primary float-right" data-toggle="modal" data-target="#upsertModal">
                Crear Producto
            </button>
        </h3>
        <table class="table" style="margin-top: 1.5rem">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Disponible</th>
                    <th>Proveedor</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${productos}">
                    <tr>
                        <td>${producto.id}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.precio}</td>
                        <td>${producto.disponible}</td>
                        <td>${producto.idProveedor}</td>
                        <td>
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#upsertModal" data-id="${producto.id}">Actualizar</button>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" data-id="${producto.id}">Eliminar</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="./upsert-modal.jsp"  %>
    <%@include file="./delete-modal.jsp" %>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/producto.controller.js"></script>
</body>
</html>
