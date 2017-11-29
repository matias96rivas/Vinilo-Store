<%-- 
    Document   : menu
    Created on : 28-11-2017, 17:49:25
    Author     : Matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../../css/login.css" rel="stylesheet" type="text/css"/>
<script src="../../js/login.js" type="text/javascript"></script>
<!-- Icon Bar (Sidebar - hidden on small screens) -->
<nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center" style="background-color: #1a1a1a; width: 8%">
    <!-- Avatar image in top left corner -->
    <img src="img/onlinelogomaker-112717-1506-9658.png" style="width:100%">
    <a href="index.jsp" class="w3-bar-item w3-button w3-padding-large w3-black ${pageContext.request.servletPath=='/index.jsp'?'active ':''}nav-link">
        <i class="fa fa-home w3-xxlarge"></i>
        <p>HOME</p>
    </a>
    <a href="ViniloServlet" class="w3-bar-item w3-button w3-padding-large w3-hover-black ${pageContext.request.servletPath=='/WEB-INF/jsp/vinilo/listar.jsp'?'active ':''}nav-link">
        <i class="fa fa-bullseye w3-xxlarge"></i>
        <p>VINILOS</p>
    </a>
    <a href="ArtistaServlet" class="w3-bar-item w3-button w3-padding-large w3-hover-black ${pageContext.request.servletPath=='/WEB-INF/jsp/artista/listar.jsp'?'active ':''}nav-link">
        <i class="fa fa-users w3-xxlarge"></i>
        <p>ARTISTAS</p>
    </a>
    <a href="GeneroServlet" class="w3-bar-item w3-button w3-padding-large w3-hover-black ${pageContext.request.servletPath=='/WEB-INF/jsp/genero/listar.jsp'?'active' :''}nav-link">
        <i class="fa fa-headphones w3-xxlarge"></i>
        <p>GENERO</p>
    </a>
    <a href="CarritoServlet" class="w3-bar-item w3-button w3-padding-large w3-hover-black ${pageContext.request.servletPath=='/WEB-INF/jsp/carrito/listar.jsp'?'active ':''}nav-link">
        <i class="fa fa-shopping-cart w3-xxlarge"></i>
        <p>CARRITO</p>
    </a>
    <a href="#contact" class="w3-bar-item w3-button w3-padding-large w3-hover-black">                
        <i class="fa fa-sign-in w3-xxlarge" onclick="document.getElementById('id01').style.display = 'block'"></i>
        <p>LOGIN</p>
    </a>

    
</nav>

<!-- Navbar on small screens (Hidden on medium and large screens) -->
<div class="w3-top w3-hide-large w3-hide-medium" id="myNavbar">
    <div class="w3-bar w3-black w3-opacity w3-hover-opacity-off w3-center w3-small">
        <a href="#" class="w3-bar-item w3-button" style="width:25% !important">HOME</a>
        <a href="#about" class="w3-bar-item w3-button" style="width:25% !important">ABOUT</a>
        <a href="#photos" class="w3-bar-item w3-button" style="width:25% !important">PHOTOS</a>
        <a href="#contact" class="w3-bar-item w3-button" style="width:25% !important">CONTACT</a>
    </div>
</div>

