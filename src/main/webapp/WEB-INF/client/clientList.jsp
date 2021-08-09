<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale  value ="es_MX" />

<%-- Iteramos lista de clientes enviadas desde el servlet controlador ClientController --%>
<section id="clients" >
    <div class ="container" >
        <div class ="row" >
            <div class ="col-md-9" >
                <div class="card" >
                    <div class ="card-header" >
                        <h4>Listado de clientes</h4>
                    </div>
                    <div class ="card-body" >
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Saldo</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%--  Iteramos cada elemento de la lista de clientes--%>
                                <c:forEach var="client"  items="${clients}" varStatus = "status" >
                                    <tr>
                                        <th>${status.count}</th>
                                        <td>${client.name} ${client.surname}</td>
                                        <td> <fmt:formatNumber  value ="${client.balance}" type ="currency" /></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/clients?action=update&id=${client.id}"
                                               ><i class="fas fa-edit text-primary"></i></a>
                                            <a href="${pageContext.request.contextPath}/clients?action=delete&id=${client.id}"
                                               ><i class="fas fa-trash text-danger"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class ="col-md-3" >
                <div class ="card text-center bg-danger text-white mb-3" >
                    <div class ="card-body" >
                        <h3>Saldo total</h3>
                        <h4 class =""display-4 >
                            <fmt:formatNumber  value = "${balanceTotal}" type = "currency" />                               
                        </h4>
                    </div>
                </div>


                <div class ="card text-center bg-success text-white mb-3" >
                    <div class ="card-body" >
                        <h3>Total de clientes</h3>
                        <h4 class =""display-4 >
                            <i class ="fas fa-users" ></i> ${clientTotal}                             
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%--  Modal agregar un nuevo cliente --%> 
<jsp:include page ="/WEB-INF/client/modalAddClient.jsp"/>
