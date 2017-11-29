<%-- 
    Document   : listar
    Created on : 28-11-2017, 23:53:09
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
        <link href="../../../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/login.js" type="text/javascript"></script>
        <title>Artistas</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/mensajes.jsp"></jsp:include>

            <div class="w3-padding-64 w3-content w3-text-grey">
                <form method="GET" action="ArtistaServlet">
                    <input type="hidden" name="op" value="buscar">
                    <div>
                        <label>Artista</label>
                        <input type="text" name="artista" id="artista" value="${fn:escapeXml(artistaBuscado)}">
                </div>
                <div>
                    <label class="sr-only" for="categoria">Género</label>
                    <select name="genero" id="genero" class="custom-select mb-2 mr-sm-2 mb-sm-0">
                        <option selected>Escoja un género</option>
                        <c:forEach items="${generos}" var="g">
                            <option value="${g.id}" ${g.id == artistaBuscado?'selected="true"':''}>${g.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button type="submit">Buscar</button>
                </div>                

                <c:if test="${empty artistas}">
                    No hay artistas para mostrar.
                </c:if>            

                <c:if test="${!empty artistas}">
                    <!-- tabla con productos -->
                    <table class="table table-striped">
                        <thead class="thead-inverse">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Pais</th>
                                <th>Estado</th>
                                <th>Descripcion</th>
                                <th>Pag. Web </th>
                                <th>Genero</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${artistas}" var="a">
                                <tr>
                                    <th>${a.id}</th>                                
                                    <td>${a.nombre}</td>  
                                    <td>${a.pais}</td> 
                                    <td>${a.estado}</td> 
                                    <td>${a.descripcion}</td> 
                                    <td>${a.web}</td> 
                                    <td>${a.genero.nombre}</td>                              

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
        </div>
    

</form>
</body>
</html>
