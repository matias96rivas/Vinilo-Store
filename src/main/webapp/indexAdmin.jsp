<%-- 
    Document   : indexAdmin
    Created on : 29-nov-2017, 16:24:26
    Author     : LC1300XXXX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link href="css/page.css" rel="stylesheet" type="text/css"/>
        <script src="js/login.js" type="text/javascript"></script>
        <title>Mantenedor</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/menuAdmin.jsp"></jsp:include>

        <div class="w3-padding-64 w3-content w3-text-grey">
            <h1>
                <div class="text-right">Bienvenido ${usuario.usuario}</div>                   
            </h1>           
            
        </div>
             
        
    </body>
</html>
