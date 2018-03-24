function sendQueryToPrintTaskServlet(value) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, GET_METHOD, printTaskTable, PRINT_TASK_SERVLET, makeRequestBody(TASK_TYPE, value));
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

    var taskTypeHeader = document.getElementById("taskTypeHeader");

    removeAllElements(taskTypeHeader);

    var h5 = document.createElement(H5_TAG);

    h5.setAttribute(ID_ATTRIBUTE, "sectionName");

    h5.setAttribute(NAME_ATTRIBUTE, taskType);

    h5.innerHTML = taskType;

    taskTypeHeader.appendChild(h5);

    var taskTable = document.getElementById("taskTable");

    if(tasks.length !== 0){
        var thead = tableHeader(taskType);

        taskTable.appendChild(thead);
    }

    for (var counter in tasks) {

        printTask(tasks[counter], isMain, isBasket, taskType);


    }

    if(taskType === BASKET) {
        var button = document.createElement(BUTTON_TAG);
        button.setAttribute(FORM_ATTRIBUTE, DELETE);
        button.innerHTML = DELETE;

        var func = function () {
            var values = getCheckedCheckBoxes(TASK_IDS);
            sendRequestToDeleteTaskServlet(values);
        };
        button.addEventListener("click", func);
        taskTable.appendChild(button);
    }
}

function printTask(task, isMain, isBasket, taskType) {
    var taskTable = document.getElementById("taskTable");

    var taskId = task.taskId;

    var taskName = task.taskName;

    var description = task.description;

    var fileName = task.fileName;

    var date = task.date;

    var tr = firstTableRow(taskId, taskName, fileName, isMain, isBasket, taskType, date);

    tr.setAttribute(NAME_ATTRIBUTE, taskId);

    taskTable.appendChild(tr);

    tr = descriptionSecondTableRow(taskName, description, date,  taskId);

    tr.setAttribute(NAME_ATTRIBUTE, taskId);

    taskTable.appendChild(tr);

    taskTable.appendChild(tr);
}

function tableHeader(taskType) {

    var thead = document.createElement("thead");
    var th = document.createElement("th");

    if (taskType === 'BASKET' || taskType === 'FIXED'){
        th.innerHTML = 'restore';
    } else{
        th.innerHTML = 'complete';
    }

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'task name';

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

        th.appendChild(checkbox);

        var label = document.createElement(LABEL_TAG);

        label.setAttribute(FOR_ATTRIBUTE,"checkAll");

        label.innerHTML = "check all";

        th.appendChild(label);

        thead.appendChild(th);
    } else {
        th = document.createElement("th");

        th.innerHTML = 'to basket';

        thead.appendChild(th);
    }

    return thead;
}

function firstTableRow(taskId, taskName, fileName, isMain, isBasket, taskType, date) {

    var tr = document.createElement(TR_TAG);

    var td = moveTaskTableData(taskId, isMain, taskType);

    tr.appendChild(td);

    td = taskNameTableData(taskName, taskId);

    tr.appendChild(td);

    td = fileNameTableData(taskId, fileName, taskName);

    tr.appendChild(td);

    if (taskType === "SOMEDAY") {

        td = dateTableData(taskId, date);

        tr.appendChild(td);
    }

    td = throwTaskTableData(taskId, isBasket);

    tr.appendChild(td);

    return tr;
}

function dateTableData(taskId, date) {

    var td = document.createElement(TD_TAG);

    td.setAttribute(ID_ATTRIBUTE, taskId + "date");

    td.innerHTML = date;

    return td;
}


function descriptionSecondTableRow(taskName, description, date,  taskId) {

    var tr = document.createElement(TR_TAG);

    tr.setAttribute(ID_ATTRIBUTE, taskName);

    tr.setAttribute(CLASS_ATTRIBUTE, MODAL_DESCRIPTION);

    var td = document.createElement(TD_TAG);

    td.setAttribute(ID_ATTRIBUTE, taskId + 'info');

    td.setAttribute(COLSPAN_ATTRIBUTE, 5);

    var h5 = document.createElement(H5_TAG);

    h5.innerHTML = "description";

    td.appendChild(h5);

    var p = document.createElement("p");

    p.setAttribute(NAME_ATTRIBUTE,taskId + 'descriptionField');

    p.innerHTML = description;

    td.appendChild(p);

    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,taskId + 'description');

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger mr-3");

    button.innerHTML = CHANGE_DESCRIPTION_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this,'description',taskId,description);

    td.appendChild(button);

    button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,taskId + 'name');

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger mr-3");

    button.innerHTML = CHANGE_NAME_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this, 'name', taskId, taskName);

    td.appendChild(button);

    button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,taskId + 'date');

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger mr-3");

    button.innerHTML = CHANGE_DATE_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this, 'date', taskId, date);

    td.appendChild(button);

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

function taskNameTableData(taskName, taskId) {

    var td = document.createElement(TD_TAG);

    var a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#" + taskName);

    a.setAttribute(ID_ATTRIBUTE,taskId + 'name');

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

        button.onclick = sendQueryToMoveTaskServlet.bind(this, taskId, locationType); //think about it

        button.innerHTML = 'Throw';

        td.appendChild(button);
    }

    else {

        var input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, CHECKBOX_TAG);

        input.setAttribute(NAME_ATTRIBUTE, TASK_IDS);

        input.setAttribute(VALUE_ATTRIBUTE, taskId);

        input.setAttribute(FORM_ATTRIBUTE, DELETE);

        td.appendChild(input);
    }

    return td;
}

