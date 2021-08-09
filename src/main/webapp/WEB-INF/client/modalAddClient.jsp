<!-- Modal -->
<div class="modal fade" id="modalAddClient" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="modalAddClient" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Nuevo cliente</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action ="${pageContext.request.contextPath}/clients?action=create" method="POST" class="was-validated" >
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name-client">Nombre</label>
                        <input type="text" class="form-control" id="name-client" name ="nameClient"  placeholder="Nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="surname">Apellidos</label>
                        <input type="text" class="form-control" id="surname" name = "surname"  placeholder="Apellidos" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name  ="email"  placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">Telefono</label>
                        <input type="tel" class="form-control" id="phone" name= "phone"  placeholder="Telefono" required >
                    </div>
                    <div class="form-group">
                        <label for="balance">Saldo</label>
                        <input type="number" class="form-control" id="balance" name ="balance"  placeholder="Saldo" required step="any" >
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"> <i class ="fas fa-times" ></i> Cerrar</button>
                    <button type="submit" class="btn btn-success"> <i class ="fas fa-save" ></i> Guardar</button>
                </div>
            </form>            
        </div>
    </div>
</div>
