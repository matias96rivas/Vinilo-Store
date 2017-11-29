<%-- 
    Document   : boleta
    Created on : 29-11-2017, 0:41:39
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
        <title>Boleta</title>
    </head>
    <body class="w3-black">
        <jsp:include page="/WEB-INF/jspf/login.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/menu.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jspf/mensajes.jsp"></jsp:include>
        <div>
            <c:if test="${empty compra.lineasCompra}">
                No se ha realizado ninguna compra.
            </c:if>            

            <c:if test="${!empty compra.lineasCompra}">
                <div id="boleta">
                    <a id="btn-imprimir" class="btn btn-primary" href="javascript:window.print();">Imprimir</a>
                    <br /><br />
                    <div class="row">
                        <div class="col">
                            <img src="/img/onlinelogomaker-112717-1506-9658.png" alt="logo" style="width: 200px;" />
                        </div>
                        <div class="col">
                            VINILO-STORE<br />
                            GIRO: Disquera<br />
                            EMAIL: contacto@vinilostore.cl<br />
                            TELÉFONO: 123456789<br />
                        </div>
                        <div class="col">
                            R.U.T.: 76.342.222-3<br />
                            BOLETA ELECTRÓNICA<br />
                            Nº ${compra.id}<br />
                            S.I.I. - SANTIAGO PONIENTE<br />                            
                        </div>
                    </div>

                    <br /><br />
                    <table border="1" style="width: 100%;">
                        <thead>
                            <tr>
                                <td>Código</td>
                                <td>Descripción</td>
                                <td>Cantidad</td>
                                <td>Precio</td>
                                <td>Valor</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${compra.lineasCompra}" var="lc">
                                <tr>
                                    <td>-</td>
                                    <td>${lc.compra.nombre}</td>
                                    <td>${lc.cantidad}</td>
                                    <td><fmt:formatNumber value="${lc.precio}" /></td>
                                    <td><fmt:formatNumber value="${lc.subtotal}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col"></div>
                        <div class="col"></div>
                        <div class="col text-right">
                            <br /><br />
                            <table>
                                <tr>
                                    <td>MONTO NETO $</td>
                                    <td><fmt:formatNumber value="${compra.total}" /></td>
                                </tr>
                                <tr>
                                    <td>I.V.A. 19% $</td>
                                    <td><fmt:formatNumber value="${compra.iva}" /></td>
                                </tr>
                                <tr>
                                    <td>IMPUESTO ADICIONAL $</td>
                                    <td>0</td>
                                </tr>
                                <tr>
                                    <td>TOTAL $</td>
                                    <td><fmt:formatNumber value="${compra.totalConIva}" /></td>
                                </tr> 
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
