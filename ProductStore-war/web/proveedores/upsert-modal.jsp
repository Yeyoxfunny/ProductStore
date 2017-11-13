<div class="modal fade" id="upsertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crear Proveedor</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="productos" method="POST" id="proveedoresForm">
                    <div class="form-group">
                        <label for="field_id">ID</label>
                        <input id="field_id" class="form-control" type="text" name="id" disabled>
                    </div>
                    <div class="form-group">
                        <label for="field_nombre">Nombre</label>
                        <input id="field_nombre" class="form-control" type="text" name="nombre">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="guardar-proveedor" type="button" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>