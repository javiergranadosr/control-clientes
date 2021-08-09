
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/c394e4529c.js" crossorigin="anonymous"></script>
        <title>Editar cliente</title>
    </head>
    <body>

        <%-- Cabecero--%>
        <jsp:include page ="/WEB-INF/includes/header.jsp"/>

        <form action="${pageContext.request.contextPath}/clients?action=clientUpdate&id=${client.id}" method="POST" class="was-validated">
            <%-- Botones de navegacion --%>
            <jsp:include page ="/WEB-INF/includes/buttonAddUpdate.jsp"/>

            <section id ="details" >
                <div class ="container " >
                    <div class ="row" >
                        <div class ="col" >
                            <div class ="card" >
                                <div class ="card-header" >
                                    <h4>Editar cliente</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="name-client">Nombre</label>
                                        <input type="text" class="form-control" id="name-client" name ="nameClient"  placeholder="Nombre" required
                                               value ="${client.name}" >
                                    </div>
                                    <div class="form-group">
                                        <label for="surname">Apellidos</label>
                                        <input type="text" class="form-control" id="surname" name = "surname"  placeholder="Apellidos" required
                                               value = "${client.surname}">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" id="email" name  ="email"  placeholder="Email" required
                                               value = "${client.email}">
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Telefono</label>
                                        <input type="tel" class="form-control" id="phone" name= "phone"  placeholder="Telefono" required 
                                               value = "${client.phone}">
                                    </div>
                                    <div class="form-group">
                                        <label for="balance">Saldo</label>
                                        <input type="number" class="form-control" id="balance" name ="balance"  placeholder="Saldo" required
                                               value = "${client.balance}" step="any" >
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </section>


        </form>
        <%-- Footer  --%>
        <jsp:include page="/WEB-INF/includes/footer.jsp"/>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
    </body>
</html>




