<%-- 
    Document   : listar
    Created on : 29-11-2017, 0:15:06
    Author     : Matias
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link href="css/page.css" rel="stylesheet" type="text/css"/>
        <script src="js/login.js" type="text/javascript"></script>
        <link href="../../../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Vinilos</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/mensajes.jsp"></jsp:include>
        <div class="w3-padding-64 w3-content w3-text-grey">
        <div class="row">
                
            
            <c:if test="${empty vinilos}">
                No hay vinilos para mostrar.
            </c:if>            

            <c:if test="${!empty vinilos}">
                <!-- tabla con productos -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>ID</th>
                            <th>Imagen</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th>Género</th>
                            <th>Artista</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${vinilos}" var="v">
                            <tr>
                                <th>${v.id}</th>
                                <td>
                                    <img src="${v.img}" alt="${v.nombre}" style="height: 100px; width: auto;" />
                                </td>
                                <td>${v.nombre}</td>
                                <td>${v.descripcion}</td>
                                <td>
                                    $<fmt:formatNumber value="${v.precio}" />
                                </td>
                                <td>${v.genero.nombre}</td>
                                <td>${v.artista.nombre}</td>
                                <td>
                                    <!-- agregar al carro -->
                                    <form method="POST" action="CarritoServlet" class="form-inline">
                                        <div class="form-group">
                                            <input name="cantidad" value="1" type="number" min="1" max="10" />
                                            <input type="hidden" name="productoId" value="${v.id}" />
                                        </div>
                                        <button type="submit" class="btn btn-primary">Add</button>
                                    </form>
                                    <!-- eliminar form> -->
                                    <form method="get" action="CarritoServlet">
                                        <input type="hidden" name="id" value="${v.id}" />
                                        <input type="hidden" name="op" value="eliminar" />
                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

    </body>
</html>
