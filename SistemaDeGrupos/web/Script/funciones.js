/*
 EIF209 - Programación 4 – Proyecto #1
 Abril 2019
 Autores:
 - 116960863 Diego Babb
 - 116920756 Naomi Rojas
 */

function init() {
    setTimeout(actualizarR, 1000);
}

function actualizarR() {
    var o = document.getElementById("orden").selectedIndex;
    if (o !== 0) {
        requestJSON(actualizarInfo, "ServletRecarga?orden=" + o.toString());
        setTimeout(actualizarR, 1000);
    } else {
        requestJSON(actualizarInfo, "ServletRecarga");
        setTimeout(actualizarR, 1000);
    }
}

function actualizarInfo(datos) {
    var reflistaUsuarios = document.getElementById("usuarios");
    if (reflistaUsuarios) {
        reflistaUsuarios.innerHTML = datos.usuarios;
    }
}

function logout() {
    document.botones.action = "ServletLogout";
    document.botones.submit();
}

function cambiarClave() {
    document.botones.action = "ServletCambiarClave";
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