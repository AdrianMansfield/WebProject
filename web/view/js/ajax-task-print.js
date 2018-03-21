function sendQueryToPrintTaskServlet(value) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, GET_METHOD, printTaskTable, PRINT_TASK_SERVLET, makeRequestBody(TASK_TYPE, value));
    var tasksTypeHeader = document.getElementById("tasksType"); // It is pizdec
    if(tasksTypeHeader) {
        tasksTypeHeader.innerHTML = value;
    }

    //xmlHttpRequest.close();
}


function printTaskTable(jsonObject) {
    removeAllElements(taskTable);
    var taskList = jsonObject.tasks;
    var taskType = jsonObject.taskType;
    drawTaskTable(taskList, taskType);

}

function drawTaskTable(tasks, taskType) {
    var isBasket = taskType === BASKET;

    var isFixed = taskType === FIXED;

    var isMain = !isBasket && !isFixed;

    var taskTable = document.getElementById("taskTable");

    var thead = tableHeader(taskType);

    taskTable.appendChild(thead);

    for (var counter in tasks) {

        var taskId = tasks[counter].taskId;

        var taskName = tasks[counter].taskName;

        var description = tasks[counter].description;

        var fileName = tasks[counter].fileName;

        var date = tasks[counter].date;

        var fileExists = fileName !== NO_FILE; // use this, Andrew ^__^

        var tr = firstTableRow(taskId, taskName, fileName, isMain, isBasket, taskType, date);

        tr.setAttribute(NAME_ATTRIBUTE, taskId);

        taskTable.appendChild(tr);

        tr = descriptionSecondTableRow(taskName, description);

        tr.setAttribute(NAME_ATTRIBUTE, taskId);

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow('description',taskId, description, taskName + CHANGE_DESCRIPTION);

        tr.setAttribute(NAME_ATTRIBUTE, taskId);

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow('name',taskId, taskName, taskName + CHANGE_NAME);

        tr.setAttribute(NAME_ATTRIBUTE, taskId);

        taskTable.appendChild(tr);

        tr = changeTaskInfoSecondRow('date',taskId, date, taskName + CHANGE_DATE);

        tr.setAttribute(NAME_ATTRIBUTE, taskId);

        taskTable.appendChild(tr);
    }
}

function tableHeader(taskType) {

    var thead = document.createElement("thead");
    var th = document.createElement("th");

    th.innerHTML = 'complete';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'description';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'file';

    thead.appendChild(th);

    if (taskType === "SOMEDAY") {

        th = document.createElement("th");

        th.innerHTML = 'date';

        thead.appendChild(th);
    }

    if (taskType === "BASKET"){
        th = document.createElement("th");

        var checkbox = document.createElement("checkbox");

        checkbox.setAttribute(ID_ATTRIBUTE,"checkAll");

        checkbox.onclick = toggleAll.bind(this,this);

        th.appendChild(checkbox);

        var label = document.createElement(LABEL_TAG);

        label.setAttribute(FOR_ATTRIBUTE,"checkAll");

        label.innerHTML = "check all";

        th.appendChild(label);

        thead.appendChild(th);
    } else {
        th = document.createElement("th");

        th.innerHTML = 'delete';

        thead.appendChild(th);
    }

    return thead;
}

function firstTableRow(taskId, taskName, fileName, isMain, isBasket, taskType, date) {

    var tr = document.createElement(TR_TAG);

    var td = moveTaskTableData(taskId, isMain, taskType);

    tr.appendChild(td);

    td = taskNameTableData(taskName);

    tr.appendChild(td);

    td = fileNameTableData(taskId, fileName, taskName);

    tr.appendChild(td);

    if (taskType === "SOMEDAY") {

        td = dateTableData(date);

        tr.appendChild(td);
    }

    td = throwTaskTableData(taskId, isBasket);

    tr.appendChild(td);

    return tr;
}

function dateTableData(date) {

    var td = document.createElement(TD_TAG);

    td.innerHTML = date;

    return td;
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

    var a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName + CHANGE_DESCRIPTION);

    a.innerHTML = CHANGE_DESCRIPTION_HREF;

    td.appendChild(a);

    a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName + CHANGE_NAME);

    a.innerHTML = CHANGE_NAME_HREF;

    td.appendChild(a);

    a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName + CHANGE_DATE);

    a.innerHTML = CHANGE_DATE_HREF;

    td.appendChild(a);

    a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = CLOSE_HREF;

    td.appendChild(a);

    tr.appendChild(td);

    return tr;
}

function changeTaskInfoSecondRow(attributeName, taskId, taskInfo, formId) {

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

    input.setAttribute(VALUE_ATTRIBUTE, attributeName);

    form.appendChild(input);

    var label = document.createElement(LABEL_TAG);

    label.innerHTML = "Enter new description";

    form.appendChild(label);

    var textarea = document.createElement(TEXTAREA_TAG);

    textarea.setAttribute(NAME_ATTRIBUTE, TASK_ATTRIBUTE);

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


function moveTaskTableData(taskId, isMain, taskType) {

    var td = document.createElement(TD_TAG);

    var form = document.createElement(FORM_ATTRIBUTE);

    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var locationType = FIXED;

    if (isMain) {
        locationType = FIXED.toLowerCase();
    }

    else {
        locationType = MAIN;
    }

    var input = document.createElement(BUTTON_TAG);

    input.setAttribute(VALUE_ATTRIBUTE, "&#10004");

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    input.onclick = sendQueryToMoveTaskServlet.bind(this, taskId, locationType);

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

    button.setAttribute(ID_ATTRIBUTE, taskName + ";" + fileName);

    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    button.onclick = drawModalWindows.bind(this, fileName, taskName);

    button.innerHTML = fileName;

    td.appendChild(button);

    return td;
}

function throwTaskTableData(taskId, isBasket) {

    var td = document.createElement(TD_TAG);

    if (!isBasket) {

        var locationType = BASKET.toLowerCase();

        var button  = document.createElement(BUTTON_TAG);

        button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");

        button.onclick = sendQueryToPrintTaskServlet.bind(this, taskId, locationType); //think about it

        button.innerHTML = 'Throw';

        td.appendChild(button);

        // var form = document.createElement("form");
        //
        // form.setAttribute(ACTION_ATTRIBUTE, MOVE_TASK_SERVLET);
        //
        // form.setAttribute(ID_ATTRIBUTE, TASK_FORM); // HM....
        //
        // form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);
        //
        // form.setAttribute(CLASS_ATTRIBUTE, "mb-0");
        //
        // var input = document.createElement(INPUT_TAG);
        //
        // input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);
        //
        // input.setAttribute(NAME_ATTRIBUTE, TASK_ID);
        //
        // input.setAttribute(VALUE_ATTRIBUTE, taskId);
        //
        // form.appendChild(input);
        //
        // input = document.createElement(INPUT_TAG);
        //
        // input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);
        //
        // input.setAttribute(NAME_ATTRIBUTE, LOCATION_TYPE);
        //
        // input.setAttribute(VALUE_ATTRIBUTE, BASKET.toLowerCase());
        //
        // form.appendChild(input);
        //
        // input = document.createElement(INPUT_TAG);
        //
        // input.setAttribute(TYPE_ATTRIBUTE, SUBMIT_ATTRIBUTE);
        //
        // input.setAttribute(VALUE_ATTRIBUTE, "&#10006");
        //
        // input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");
        //
        // form.appendChild(input);
        //
        // td.appendChild(form);
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

