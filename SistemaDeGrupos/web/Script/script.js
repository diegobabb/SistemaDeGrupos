
function logout() {
    document.botones.action = "ServletLogout";
    document.botones.submit();
}

function datos() {
    document.botones.action = "ServletDatos";
    document.botones.submit();
}

function cursos() {
    document.botones.action = "ServletMatricula";
    document.botones.submit();
}

function matricular(row) {
    var s = "ServletMatricular?curso=" + row.toString();
    requestJSON(actualizar, s);
}


function desmatricular(row) {
    var s = "ServletDesmatricular?curso=" + row.toString();
    requestJSON(actualizar, s);
}

function actualizar(data) {
    var refCurNoMat = document.getElementById("CurNoMat");
    var refCurMat = document.getElementById("CurMat");
    if (refCurNoMat) {
        refCurNoMat.innerHTML = data.CurNoMat;
    }
    if (refCurMat) {
        refCurMat.innerHTML = data.CurMat;
    }
}