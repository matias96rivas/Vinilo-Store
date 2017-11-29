<%-- 
    Document   : listar
    Created on : 29-11-2017, 0:33:51
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
        <title>Compras</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/mensajes.jsp"></jsp:include>
        <div class="container">
                      
            <c:if test="${empty compras}">
                No hay compras para mostrar.
            </c:if>            

            <c:if test="${!empty compras}">
                <!-- tabla con pedidos -->
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>Número</th>                            
                            <th>Monto</th>                           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${compras}" var="c">
                            <tr>
                                <th>${c.id}</th>                                
                                
                                <td>
                                    $<fmt:formatNumber value="${c.total}" />
                                </td>
                                                              
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>
