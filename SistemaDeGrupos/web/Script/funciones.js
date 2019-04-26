/*
 EIF209 - Programación 4 – Proyecto #1
 Abril 2019
 Autores:
 - 116960863 Diego Babb
 - 116920756 Naomi Rojas
 */
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
    var s = "ServletEliminarGrupo?grupo=" + row.toString();
    requestJSON(actualizar, s);
}
function eliminaGrupo(row) {
    var s = "ServletDeleteGrupo?grupo=" + row.toString();
    requestJSON(actualizar, s);
}
function actualizar(data) {
    var refmiscursos = document.getElementById("miscursos");
    if (refmiscursos) {
        refmiscursos.innerHTML = data.miscursos;
    }
}

function agregar(row) {
    console.log(row);
    var s = "ServletAgregarGrupo?grupo=" + row.toString();
    requestJSON(actualizar, s);
}