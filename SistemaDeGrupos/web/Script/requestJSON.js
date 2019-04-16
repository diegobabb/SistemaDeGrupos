
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