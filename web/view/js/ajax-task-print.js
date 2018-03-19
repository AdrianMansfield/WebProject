function sendQueryToPrintTaskServlet(value) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, "get", printTaskTable, "/PrintTaskServlet", makeRequestBody("taskType", value));
    //xmlHttpRequest.close();
}



function printTaskTable(jsonObject) {
    removeAllElements(taskTable);
    var taskList = jsonObject.tasks;
    var taskType = jsonObject.taskType;
    drawTaskTable(taskList,taskType);

}

function drawTaskTable(tasks, taskType) {
    var isBasket = taskType === "BASKET";

    var isFixed = taskType === "FIXED";

    var isMain = !isBasket && !isFixed;

    for(var counter in tasks) {

        var taskId = tasks[counter].taskId;

        var taskName = tasks[counter].taskName;

        var description = tasks[counter].description;

        var fileName = tasks[counter].fileName;

        var fileExists = fileName !== "No file";

        var tr = firstTableRow(taskId, taskName, fileName, isMain, isBasket);

        taskTable.appendChild(tr);

        tr = descriptionSecondTableRow(taskName, description);

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow(taskId, description, taskName + "ChangeDescription");

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow(taskId, taskName, taskName + "ChangeName");

        taskTable.appendChild(tr);


    }
}


function firstTableRow(taskId, taskName, fileName, isMain, isBasket) {

    var tr = document.createElement(TR_TAG);

    var td = moveTaskTableData(taskId, isMain);

    tr.appendChild(td);

    td = taskNameTableData(taskName);

    tr.appendChild(td);

    td = fileNameTableData(taskId, fileName, taskName);

    tr.appendChild(td);

    td = throwTaskTabledata(taskId, isBasket);

    tr.appendChild(td);

    return tr;
}


function descriptionSecondTableRow(taskName, description) {

    var tr = document.createElement(TR_TAG);

    tr.setAttribute(ID_ATTRIBUTE, taskName);

    tr.setAttribute(CLASS_ATTRIBUTE, "modalDescription");

    var td = document.createElement(TD_TAG);

    td.setAttribute("colspan", 5);

    var p = document.createElement("p");

    p.innerHTML = description;

    td.appendChild(p);

    var a = document.createElement("a");

    a.setAttribute("href", "#" + taskName + "ChangeDescription");

    a.innerHTML = "Change description";

    td.appendChild(a);

    a = document.createElement("a");

    a.setAttribute("href", "#" + taskName + "ChangeName");

    a.innerHTML = "Change name";

    td.appendChild(a);

    a = document.createElement("a");

    a.setAttribute("href", "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = "close";

    td.appendChild(a);

    tr.appendChild(td);

    return tr;
}

function changeTaskInfoSecondRow(taskId, taskInfo, formId) {

    var tr = document.createElement(TR_TAG);

    tr.setAttribute(ID_ATTRIBUTE, formId);

    tr.setAttribute(CLASS_ATTRIBUTE, "modalDescription");

    var td = document.createElement(TD_TAG);

    td.setAttribute("colspan", 5);

    var form = document.createElement("form");

    form.setAttribute("action", "ChangeTaskInfoServlet");

    form.setAttribute("method", POST_METHOD);

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, "hidden");

    input.setAttribute(NAME_ATTRIBUTE, "taskId");

    input.setAttribute(VALUE_ATTRIBUTE, taskId);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, "hidden");

    input.setAttribute(NAME_ATTRIBUTE, "infoType");

    input.setAttribute(VALUE_ATTRIBUTE, "description");

    form.appendChild(input);

    var textarea = document.createElement("textarea");

    textarea.setAttribute(NAME_ATTRIBUTE, "description");

    textarea.innerHTML = taskInfo;

    form.appendChild(textarea);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, "submit");

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-sm btn-outline-danger");

    form.appendChild(input);

    td.appendChild(form);

    var a = document.createElement("a");

    a.setAttribute("href", "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = "close";

    td.appendChild(a);

    tr.appendChild(td);

    return tr;

}



function moveTaskTableData(taskId, isMain) {

    var td = document.createElement(TD_TAG);

    var form = document.createElement("form");

    form.setAttribute("action", "MoveTaskServlet");

    form.setAttribute("method", POST_METHOD);

    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, "hidden");

    input.setAttribute(NAME_ATTRIBUTE, "taskId");

    input.setAttribute(VALUE_ATTRIBUTE, taskId);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, "hidden");

    input.setAttribute(NAME_ATTRIBUTE, "locationType");

    if(isMain) {
        input.setAttribute(VALUE_ATTRIBUTE, "fixed");
    }

    else {
        input.setAttribute(VALUE_ATTRIBUTE, "main");
    }

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, "submit");

    input.setAttribute(VALUE_ATTRIBUTE, "&#10004");

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    form.appendChild(input);

    td.appendChild(form);

    return td;
}

function taskNameTableData(taskName) {

    var td = document.createElement(TD_TAG);

    var a = document.createElement("a");

    a.setAttribute("href", "#" + taskName);

    a.innerHTML = taskName;

    td.appendChild(a);

    return td;
}

function fileNameTableData(taskId, fileName, taskName) {

    var td = document.createElement(TD_TAG);
    var button = document.createElement("button");

    button.setAttribute("class","btn btn-outline-danger");
    button.setAttribute("onclick","drawModalWindow("+taskId+",'"+fileName+"',"+taskName+")");

    button.innerHTML = fileName;

    td.appendChild(button);

    return td;
}

function throwTaskTabledata(taskId, isBasket) {

    var td = document.createElement(TD_TAG);

    if(!isBasket) {
        var form = document.createElement("form");

        form.setAttribute("action", "MoveTaskServlet");

        form.setAttribute(ID_ATTRIBUTE, "taskForm"); // HM....

        form.setAttribute("method", POST_METHOD);

        form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

        var input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, "hidden");

        input.setAttribute(NAME_ATTRIBUTE, "taskId");

        input.setAttribute(VALUE_ATTRIBUTE, taskId);

        form.appendChild(input);

        input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, "hidden");

        input.setAttribute(NAME_ATTRIBUTE, "locationType");

        input.setAttribute(VALUE_ATTRIBUTE, "basket");

        form.appendChild(input);

        input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, "submit");

        input.setAttribute(VALUE_ATTRIBUTE, "&#10006");

        input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

        form.appendChild(input);

        td.appendChild(form);
    }

    else {

        input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, "checkbox");

        input.setAttribute(NAME_ATTRIBUTE, "taskIds");

        input.setAttribute(VALUE_ATTRIBUTE, taskId);

        input.setAttribute(FORM_ATTRIBUTE, "delete");

        td.appendChild(input);
    }

    return td;
}

