
function logout() {
    document.botones.action = "ServletLogout";
    document.botones.submit();
}

function consultarUsuarios() {
    document.botones.action = "ServletConsultarUsuarios";
    document.botones.submit();
}

function consultarGrupos() {
    document.botones.action = "ServletConsultarGrupos";
    document.botones.submit();
}

function crearGrupo() {
    document.botones.action = "ServletCrearGrupo";
    document.botones.submit();
}

function eliminar(row) {
    var s = "ServletEliminarCurso?curso=" + row.toString();
    requestJSON(actualizar, s);
}

function actualizar(data) {
    var refmiscursos = document.getElementById("miscursos");
    if (refmiscursos) {
        refmiscursos.innerHTML = data.miscursos;
    }
}

function agregar(row) {
    var s = "ServletAgregarCurso?curso=" + row.toString();
    requestJSON(actualizar, s);
}