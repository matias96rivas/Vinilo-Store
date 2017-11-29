<%-- 
    Document   : carrito
    Created on : 29-11-2017, 0:37:22
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
        <title>Carrito de compras</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/mensajes.jsp"></jsp:include>
        <div>
            <c:if test="${empty compra.lineasCompra}">
                No hay productos en el carrito.
            </c:if>            

            <c:if test="${!empty compra.lineasCompra}">
                <h1>Carrito de Compras</h1>

                <form method="get" action="carrito">
                    <fieldset>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Comprar</button>
                            </div>
                        </div>

                    </fieldset>

                    <input type="hidden" name="op" value="comprar" />

                </form>

                <!-- tabla con productos -->
                TOTAL: $<fmt:formatNumber value="${compra.total}" />
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>Imagen</th>
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${compra.lineasCompra}" var="lc">
                            <tr>
                                <td>
                                    <img src="${lc.compra.imagen}" alt="${lc.compra.nombre}" style="height: 100px; width: auto;" />
                                </td>
                                <td>${lc.compra.nombre}</td>
                                <td>
                                    $<fmt:formatNumber value="${lc.precio}" />
                                </td>
                                <td>${lc.cantidad}</td>
                                <td>
                                    $<fmt:formatNumber value="${lc.subtotal}" />
                                </td>                                
                                <td>
                                    <form method="GET" action="CarritoServlet" class="form-inline">
                                        <input type="hidden" name="op" value="quitar" />
                                        <input type="hidden" name="productoId" value="${lc.compra.id}" />
                                        <button type="submit" class="btn btn-primary">Quitar</button>
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
