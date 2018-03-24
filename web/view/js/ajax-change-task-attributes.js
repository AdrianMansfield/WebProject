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


function sendRequestToChangeTaskInfo(taskId, taskAttribute, infoType) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, POST_METHOD, changeAttribute, "/ChangeTaskInfo",
        makeRequestBody(TASK_ID, taskId, TASK_ATTRIBUTE, taskAttribute, INFO_TYPE, infoType));
}

function changeAttribute(jSonObject) {

    var taskId = jSonObject["taskId"];

    var infoType = jSonObject["infoType"];

    var taskAttribute = jSonObject["taskAttribute"];

    actionType[infoType](taskId, taskAttribute, infoType);

}


function changeDate(taskId, date, infoType) {

    var tomorrowDate = new Date();

    tomorrowDate.setDate(tomorrowDate.getDate() + 1);

    var taskDate = Date.parse(date);

    var section;

    if(taskDate === tomorrowDate) {
        section = "TOMORROW";
    }

    else if(taskDate<tomorrowDate) {
        section = "TODAY";
    }
    else {
        section = "SOMEDAY";
    }

    var currentTaskType = document.getElementById("sectionName").getAttribute("name");

    if(currentTaskType === section) {
        sectionType[section](taskId, date, infoType);
    }
}

function change(taskId, taskAttribute, infoType) {

    var taskWindow = document.getElementById(taskId + "infoTask");

    var attributeElement = taskWindow.getElementsByName(infoType);

    attributeElement.onclick = drawChangeInfoModalWindow.bind(this, infoType, taskId, taskAttribute);

}

function changeRecord(taskId, taskAttribute, infoType) {

    var attributeCell = document.getElementById(taskId + infoType);

    attributeCell.innerHTML = taskAttribute;

    change(taskId, taskAttribute, infoType);
}
