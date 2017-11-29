<%-- 
    Document   : login
    Created on : 28-11-2017, 19:19:40
    Author     : Matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="../../css/login.css" rel="stylesheet" type="text/css"/>
<script src="../../js/login.js" type="text/javascript"></script>

<!-- Login -->
<div id="id01" class="modal">

    <form class="modal-content animate" method="POST" action="login">
        <div class="imgcontainer">
            <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
            <img src="${pageContext.request.contextPath}/img/onlinelogomaker-112717-1506-9658.png" alt="Avatar" class="avatar">
        </div>

        <div class="container">
            <label><b>Nombre:</b></label>
            <input type="text" placeholder="Ingrese su nombre de usuario" name="uname" required>

            <label><b>Contraseña</b></label>
            <input type="password" placeholder="Ingrese su contraseña" name="psw" required>

            <button type="submit">Login</button>
            <input type="checkbox" checked="checked"> Recordarme
        </div>

        <div class="container" style="background-color:#000">
            <button style="text-align: center" type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancelar</button>

        </div>
    </form>
</div>