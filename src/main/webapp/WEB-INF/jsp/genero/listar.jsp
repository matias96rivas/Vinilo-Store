<%-- 
    Document   : listar
    Created on : 28-11-2017, 23:02:02
    Author     : Matias
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Generos</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>

            <div class="w3-padding-64 w3-content w3-text-grey">
                <div>
                    <div>
                        <a href="generos?op=crear">Crear Genero</a>
                    </div>
                </div>

            <c:if test="${empty generos}">
                No existen géneros que mostrar.
            </c:if>

            <c:if test="${!empty generos}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Géneros</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${generos}" var="g">
                            <tr>
                                <td>${g.id}</td>
                                <td>${g.nombre}</td>
                                <td>
                                    <form method="GET" action="GeneroServlet">
                                        <input type="hidden" name="op" value="eliminar">
                                        <input type="hidden" name="id" value="${g.id}">
                                        <button type="submit">Eliminar</button>
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
