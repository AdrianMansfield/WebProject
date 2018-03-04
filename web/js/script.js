function initDisplay(){
        document.getElementById('rightSlide').style.display = 'none';
        document.getElementById('conference').style.display = 'none';
        document.getElementById('fixed').style.display = 'none';
        document.getElementById('bucket').style.display = 'none';
}

function showConference() {
    document.getElementById('conference').style.display = 'block';
    document.getElementById('fixed').style.display = 'none';
    document.getElementById('bucket').style.display = 'none';
}

function showEvents() {
    document.getElementById('events').style.display = 'block';
}

function addStatement(statement, checkboxId){
    var checkboxArray = document.getElementById(checkboxId);
    for (var i = 0; i < (checkboxArray.length); i++) {
        checkboxArray[i].checked = statement;
    }
}

function showDeleteButton(checkboxId, buttonId) {
    var checkboxArray = document.getElementById(checkboxId);
    var visible = 'none';
    for (var i = 0; i < (checkboxArray.length); i++) {
        if (checkboxArray[i].checked){
            visible = 'inline-block';
        }
    }
    document.getElementById(buttonId).style.display = visible;
}