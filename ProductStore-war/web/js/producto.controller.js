(function () {
    var URL_PRODUCTO = '/ProductStore-war/productos';
    var URL_PROVEEDORES = '/ProductStore-war/proveedores';
    
    var esActualizar = false;
    
    var idRef = document.getElementById('field_id');
    var nombreRef = document.getElementById('field_nombre');
    var precioRef = document.getElementById('field_precio');
    var proveedorRef = document.getElementById('field_proveedor');
    var disponibleRef = document.getElementById('field_disponible');
    
    function guardarProducto() {
        var producto = 'nombre=' + nombreRef.value + '&precio=' + precioRef.value + '&disponible=' + disponibleRef.checked + '&idProveedor=' + proveedorRef.value;
        if (esActualizar) {
            var putUrl = URL_PRODUCTO + '?id=' + idRef.value + '&' + producto;
            makeRequest('PUT', putUrl, null, manejarRespuesta);
        } else {
            makeRequest('POST', URL_PRODUCTO, producto, manejarRespuesta);
        }
    }

    function eliminarProducto(event) {
        var botonEliminar = event.target;
        var idProducto = '?id=' + botonEliminar.getAttribute('data-id');
        makeRequest('DELETE', URL_PRODUCTO + idProducto, null, manejarRespuesta);
    }

    function manejarRespuesta(err, response) {
        if (err) {
            console.log('Ocurrio un error');
            console.error(err);
        } else {
            location.reload();
        }
    }
    
    // Cuando se abre el modal upsert verifica si se abrió desde el boton actualizar
    $('#upsertModal').on('show.bs.modal', function (event) {
        var boton = $(event.relatedTarget);
        var idProducto = boton.data('id');
        renderProveedoresSelect();
        if (idProducto) {
            esActualizar = true;
            makeRequest('GET', URL_PRODUCTO + '?id=' + idProducto, null, renderProducto);
        }
        // Ocultar el input del id cuando se va a crear un producto
        idRef.parentElement.hidden = !esActualizar;
    });

    $('#upsertModal').on('hide.bs.modal', function (event) {
        document.getElementById('productosForm').reset();
        esActualizar = false;
    });

    function renderProducto(error, response) {
        if (error) {
            console.log('Error');
            console.log(error);
        }
        var producto = JSON.parse(response);
        idRef.value = producto.id;
        nombreRef.value = producto.nombre;
        precioRef.value = producto.precio;
        disponibleRef.checked = producto.disponible;
        proveedorRef.value = producto.idProveedor;
    }
    
    function renderProveedoresSelect() {
        makeRequest('GET', URL_PROVEEDORES, null, function(err, response) {
            var proveedores = JSON.parse(response);
            var htmlOutput = '';
            for(var index in proveedores) {
                var proveedor = proveedores[index];
                htmlOutput += `<option value="${proveedor.id}">${proveedor.nombre}</option>`;
            }
            proveedorRef.innerHTML = htmlOutput;
        });
    }

    document.getElementById('guardar-producto').addEventListener('click', guardarProducto);
    document.getElementById('confirmDeleteModal').addEventListener('click', eliminarProducto);
})();
