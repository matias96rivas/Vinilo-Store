<%-- 
    Document   : crear
    Created on : 28-11-2017, 21:42:59
    Author     : Matias
--%>
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
        <title>Crear Genero</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/mensajes.jsp"></jsp:include>

        <div class="w3-padding-64 w3-content w3-text-grey">
            <h2 class="w3-text-light-grey">Crear Género</h2>
            <hr style="width:200px" class="w3-opacity">

            <br>
            <p>Ingrese los datos del género a crear:</p>

            <form method="POST" action="GeneroServlet" target="_blank">
                <p><input value="${!empty genero?genero.nombre:''}" class="w3-input w3-padding-16" type="text" placeholder="Nombre:" required name="genero"></p>
                <p><input value="${!empty genero?genero.descripcion: ''}" class="w3-input w3-padding-16" type="text" placeholder="Descripción:" required name="descripcion"></p>
                <p>
                    <button class="w3-button w3-light-grey w3-padding-large" type="submit">
                        <i class="fa fa-plus"></i> GUARDAR
                    </button>
                </p>
            </form>
            <!-- End Contact Section -->
        </div>
    </body>
</html>
