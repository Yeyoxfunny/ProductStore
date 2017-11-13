function makeRequest(method, url, body, callback) {
    console.log('Request: ' + method + ' URL: ' + url + ' Body: ' + body);
    var http = new XMLHttpRequest();
    http.open(method, url, true);
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    http.onreadystatechange = function () {
        if (http.readyState !== XMLHttpRequest.DONE || http.status > 300 || http.status < 200) return;        
        callback(null, http.responseText);
    };
    http.onerror = callback;
    http.send(body);
}

// Agrega un atributo id-producto para el botón de confirmación de eliminación
$('#deleteModal').on('show.bs.modal', function (event) {
    var boton = $(event.relatedTarget);
    var entityId = boton.data('id');
    document.getElementById('confirmDeleteModal').setAttribute('data-id', entityId);
});

// Cuando se cierra el modal se elimina atributo id-producto, del botón de confirmación de eliminación
$('#deleteModal').on('hide.bs.modal', function (event) {
    document.getElementById('confirmDeleteModal').removeAttribute('data-id');
});