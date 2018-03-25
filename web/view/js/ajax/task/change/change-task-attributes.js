var actionType = {
    "date" : changeDate,
    "name" : changeRecord,
    "description" : changeRecord
};


var sectionType = {
    "TOMORROW" : change,
    "TODAY" : change,
    "SOMEDAY" : changeRecord
};

var actionNumber = {
    "date" : 2,
    "name" : 1,
    "description" : 0
};




function changeAttribute(jSonObject) {

    var taskId = jSonObject["taskId"];

    var infoType = jSonObject["infoType"];

    infoType = infoType.toLowerCase();

    var taskAttribute = jSonObject["taskAttribute"];

    actionType[infoType](taskId, taskAttribute, infoType);

}

function change(taskId, taskAttribute, infoType) {

    var taskWindow = document.getElementById(taskId + "info");

    var attributeElement = taskWindow.getElementsByTagName(BUTTON_TAG)[actionNumber[infoType]];

    attributeElement.onclick = drawChangeInfoModalWindow.bind(this, infoType, taskId, taskAttribute);

}

function changeRecord(taskId, taskAttribute, infoType) {

    var attributeCell = document.getElementById(taskId + infoType);

    attributeCell.innerHTML = taskAttribute;

    change(taskId, taskAttribute, infoType);
}


