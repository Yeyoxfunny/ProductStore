(function() {
    
    var URL_PROVEEDORES = '/ProductStore-war/proveedores';
    
    // Modal Inputs
    var idRef = document.getElementById('field_id');
    var nombreRef = document.getElementById('field_nombre');
    var esActualizar = false;
    
    function guardar() {
        var proveedor = 'nombre=' + nombreRef.value;
        if (esActualizar) {
            var putUrl = URL_PROVEEDORES + '?id=' + idRef.value + '&' + proveedor;
            makeRequest('PUT', putUrl, null, manejarRespuesta);
        } else {
            makeRequest('POST', URL_PROVEEDORES, proveedor, manejarRespuesta);
        }
    }
    
    function eliminar(event) {
        var botonEliminar = event.target;
        var idProveedor = '?id=' + botonEliminar.getAttribute('data-id');
        makeRequest('DELETE', URL_PROVEEDORES + idProveedor, null, manejarRespuesta);
    }
    
    
    // Cuando se abre el modal upsert verifica si se abrió desde el boton actualizar
    $('#upsertModal').on('show.bs.modal', function (event) {
        var boton = $(event.relatedTarget);
        var idProveedor = boton.data('id');
        if (idProveedor) {
            esActualizar = true;
            makeRequest('GET', URL_PROVEEDORES + '?id=' + idProveedor, null, renderProveedor);
        }
        // Ocultar el input del id cuando se va a crear un producto
        idRef.parentElement.hidden = !esActualizar;
    });
    
    $('#upsertModal').on('hide.bs.modal', function (event) {
        document.getElementById('proveedoresForm').reset();
        esActualizar = false;
    });
    
    function renderProveedor(error, response) {
        if (error) {
            console.error(error);
        }
        var proveedor = JSON.parse(response);
        idRef.value = proveedor.id;
        nombreRef.value = proveedor.nombre;
    }
    
    function manejarRespuesta(err, response) {
        console.log(response);
        if (err) {
            console.log('Ocurrio un error');
            console.error(err);
        } else {
            location.reload();
        }
    }
    
    document.getElementById('guardar-proveedor').addEventListener('click', guardar);
    document.getElementById('confirmDeleteModal').addEventListener('click', eliminar);
    
    // Renderizar Proveedores en la tabla
    window.onload = function() {
        var table = document.getElementById('proveedores-table');
        makeRequest('GET', URL_PROVEEDORES, null, function(err, response) {
            var proveedores = JSON.parse(response);
            var htmlOutput = '';
            for(var index in proveedores) {
                var proveedor = proveedores[index];
                htmlOutput +=
                `<tr>
                    <td>${proveedor.id}</td>
                    <td>${proveedor.nombre}</td>
                    <td>
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#upsertModal" data-id="${proveedor.id}">Actualizar</button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" data-id="${proveedor.id}">Eliminar</button>
                    </td>
                </tr>`;
            }
            table.innerHTML = htmlOutput;
        });
    };
})();