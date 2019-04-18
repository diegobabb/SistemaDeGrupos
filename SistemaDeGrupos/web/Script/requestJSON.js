/*
    EIF209 - Programación 4 – Proyecto #1
    Abril 2019
    Autores:
    - 116960863 Diego Babbb
    - 116920756 Naomi Rojas
*/
function requestJSON(callback, jsonFile) {
    fetch(jsonFile).then(
            (result) => {
        return result.json();
    }
    ).then(
            (data) => {
        callback(data);
    }
    );
}