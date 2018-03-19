function sendQueryToPrintTaskServlet(value) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, GET_METHOD, printTaskTable, PRINT_TASK_SERVLET, makeRequestBody(TASK_TYPE, value));
    //xmlHttpRequest.close();
}



function printTaskTable(jsonObject) {
    removeAllElements(taskTable);
    var taskList = jsonObject.tasks;
    var taskType = jsonObject.taskType;
    drawTaskTable(taskList,taskType);

}

function drawTaskTable(tasks, taskType) {
    var isBasket = taskType === BASKET;

    var isFixed = taskType === FIXED;

    var isMain = !isBasket && !isFixed;

    for(var counter in tasks) {

        var taskId = tasks[counter].taskId;

        var taskName = tasks[counter].taskName;

        var description = tasks[counter].description;

        var fileName = tasks[counter].fileName;

        var fileExists = fileName !== NO_FILE;

        var tr = firstTableRow(taskId, taskName, fileName, isMain, isBasket);

        taskTable.appendChild(tr);

        tr = descriptionSecondTableRow(taskName, description);

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow(taskId, description, taskName + CHANGE_DESCRIPTION);

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow(taskId, taskName, taskName + CHANGE_NAME);

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

    tr.setAttribute(CLASS_ATTRIBUTE, MODAL_DESCRIPTION);

    var td = document.createElement(TD_TAG);

    td.setAttribute(COLSPAN_ATTRIBUTE, 5);

    var p = document.createElement("p");

    p.innerHTML = description;

    td.appendChild(p);

    var a = document.createElement("a");

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName + CHANGE_DESCRIPTION);

    a.innerHTML = CHANGE_DESCRIPTION_HREF;

    td.appendChild(a);

    a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName + CHANGE_NAME);

    a.innerHTML = CHANGE_NAME_HREF;

    td.appendChild(a);

    a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = CLOSE_HREF;

    td.appendChild(a);

    tr.appendChild(td);

    return tr;
}

function changeTaskInfoSecondRow(taskId, taskInfo, formId) {

    var tr = document.createElement(TR_TAG);

    tr.setAttribute(ID_ATTRIBUTE, formId);

    tr.setAttribute(CLASS_ATTRIBUTE, MODAL_DESCRIPTION);

    var td = document.createElement(TD_TAG);

    td.setAttribute(COLSPAN_ATTRIBUTE, 5);

    var form = document.createElement("form");

    form.setAttribute(ACTION_ATTRIBUTE, CHANGE_TASK_INFO_SERVLET);

    form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, TASK_ID);

    input.setAttribute(VALUE_ATTRIBUTE, taskId);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, INFO_TYPE);

    input.setAttribute(VALUE_ATTRIBUTE, DESCRIPTION);

    form.appendChild(input);

    var textarea = document.createElement(TEXTAREA_TAG);

    textarea.setAttribute(NAME_ATTRIBUTE, DESCRIPTION);

    textarea.innerHTML = taskInfo;

    form.appendChild(textarea);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, SUBMIT_ATTRIBUTE);

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-sm btn-outline-danger");

    form.appendChild(input);

    td.appendChild(form);

    var a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = CLOSE_HREF;

    td.appendChild(a);

    tr.appendChild(td);

    return tr;

}



function moveTaskTableData(taskId, isMain) {

    var td = document.createElement(TD_TAG);

    var form = document.createElement(FORM_ATTRIBUTE);

    form.setAttribute(ACTION_ATTRIBUTE, MOVE_TASK_SERVLET);

    form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);

    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, TASK_ID);

    input.setAttribute(VALUE_ATTRIBUTE, taskId);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, LOCATION_TYPE);

    if(isMain) {
        input.setAttribute(VALUE_ATTRIBUTE, FIXED.toLowerCase());
    }

    else {
        input.setAttribute(VALUE_ATTRIBUTE, MAIN);
    }

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, SUBMIT_ATTRIBUTE);

    input.setAttribute(VALUE_ATTRIBUTE, "&#10004");

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    form.appendChild(input);

    td.appendChild(form);

    return td;
}

function taskNameTableData(taskName) {

    var td = document.createElement(TD_TAG);

    var a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName);

    a.innerHTML = taskName;

    td.appendChild(a);

    return td;
}

function fileNameTableData(taskId, fileName, taskName) {

    var td = document.createElement(TD_TAG);
    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");
    button.setAttribute(ONCLICK_ATTRIBUTE,"drawModalWindows("+taskId+",'"+fileName+"',"+taskName+")");

    button.innerHTML = fileName;

    td.appendChild(button);

    return td;
}

function throwTaskTabledata(taskId, isBasket) {

    var td = document.createElement(TD_TAG);

    if(!isBasket) {
        var form = document.createElement("form");

        form.setAttribute(ACTION_ATTRIBUTE, MOVE_TASK_SERVLET);

        form.setAttribute(ID_ATTRIBUTE, TASK_FORM); // HM....

        form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);

        form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

        var input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

        input.setAttribute(NAME_ATTRIBUTE, TASK_ID);

        input.setAttribute(VALUE_ATTRIBUTE, taskId);

        form.appendChild(input);

        input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

        input.setAttribute(NAME_ATTRIBUTE, LOCATION_TYPE);

        input.setAttribute(VALUE_ATTRIBUTE, BASKET.toLowerCase());

        form.appendChild(input);

        input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, SUBMIT_ATTRIBUTE);

        input.setAttribute(VALUE_ATTRIBUTE, "&#10006");

        input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

        form.appendChild(input);

        td.appendChild(form);
    }

    else {

        input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, CHECKBOX_TAG);

        input.setAttribute(NAME_ATTRIBUTE, TASK_IDS);

        input.setAttribute(VALUE_ATTRIBUTE, taskId);

        input.setAttribute(FORM_ATTRIBUTE, DELETE);

        td.appendChild(input);
    }

    return td;
}

