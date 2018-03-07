function printConferenceResponseHandler() {
    if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
        var data = xmlHttpRequest.responseText;
        printTaskTable(JSON.parse(data));
    }
}

function sendQueryToPrintConferenceServlet(value) {
    sendQuery(POST_METHOD, printConferenceResponseHandler, "/PrintConferenceServlet", formQuery("dateType", value));
}


function getMapFromJson(map) {
    var objectMap = {};
    for (var i in map) {
        objectMap[map[i].taskName] = map[i].fileName;
    }
    return objectMap;
}


function printTaskTable(jsonObject) {
    removeAllElements(taskTable);
    var conferenceList = jsonObject.conferenceList;
    var fileMap = getMapFromJson(jsonObject.fileMap);
    var isBasket = jsonObject.isBasket;
    drawTaskTable(conferenceList, fileMap, isBasket);

}

function drawTaskTable(conferenceList, fileMap, isBasket) {
    for (var counter in conferenceList) {
        var currentTaskId = conferenceList[counter].id;
        var currentTask = conferenceList[counter].taskName;
        var tr = document.createElement(TR_TAG);
        tr.setAttribute(ID_ATTRIBUTE, currentTaskId);
        taskTable.appendChild(tr);
        var td = document.createElement(TD_TAG);
        tr.appendChild(td);
        var input = document.createElement(INPUT_TAG);
        input.setAttribute(NAME_ATTRIBUTE, "completeAttribute");
        input.setAttribute(TYPE_ATTRIBUTE, "button");
        input.setAttribute(VALUE_ATTRIBUTE, "d");
        input.setAttribute(ID_ATTRIBUTE,currentTaskId);
        td.appendChild(input);
        td = document.createElement(TD_TAG);
        tr.appendChild(td);
        input = document.createElement(INPUT_TAG);
        input.setAttribute(NAME_ATTRIBUTE, "currentTask");
        input.setAttribute(CLASS_ATTRIBUTE, "invisible-circle-label");
        input.setAttribute(VALUE_ATTRIBUTE, currentTaskId);
        //input.setAttribute(ID_ATTRIBUTE, currentTaskId);
        input.setAttribute(ONCLICK_ATTRIBUTE, "showEvents(); sendQueryToPrintEventServlet(value); return false;"); /// <------
        td.appendChild(input);
        var label = document.createElement(LABEL_TAG);
        label.setAttribute(FOR_ATTRIBUTE, currentTaskId);
        label.innerHTML = currentTask;
        td.appendChild(label);
        td = document.createElement(TD_TAG);
        tr.appendChild(td);
        var fileName = fileMap[currentTask];

        var label = document.createElement(LABEL_TAG);
        label.setAttribute(NAME_ATTRIBUTE, "fileLabel");
        label.setAttribute(CLASS_ATTRIBUTE, "holder");
        if (fileName) {
            label.innerHTML = fileName;
            fileName = currentTaskId + ":" + currentTask + ";" + fileName;
            var div = document.createElement("div");
            div.setAttribute(CLASS_ATTRIBUTE, "block");
            label.appendChild(div);
            var button = document.createElement(BUTTON_TAG);
            button.setAttribute(FORM_ATTRIBUTE, "tasks");
            button.setAttribute(FORMACTION_ATTRIBUTE, "DownloadFileServlet");
            button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");
            button.innerHTML  = "Download";
            button.setAttribute(FORMMETHOD_ATTRIBUTE, POST_METHOD);
            button.setAttribute(NAME_ATTRIBUTE, "file");
            fileName = currentTaskId + ":" + currentTask + ";" + fileName;
            div.appendChild(button);
            var button = document.createElement(BUTTON_TAG);
            button.setAttribute(FORM_ATTRIBUTE, "tasks");
            button.setAttribute(FORMACTION_ATTRIBUTE, "DeleteFileServlet");
            button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");
            button.innerHTML  = "Delete";
            button.setAttribute(FORMMETHOD_ATTRIBUTE, POST_METHOD);
            button.setAttribute(NAME_ATTRIBUTE, "file");
            fileName = currentTaskId + ":" + currentTask + ";" + fileName;
            div.appendChild(button);
        } else {
            fileName = currentTaskId + ":" + currentTask + ";" + "No file";
            label.innerHTML = "No file";
            var div = document.createElement("div");
            div.setAttribute(CLASS_ATTRIBUTE, "block");
            label.appendChild(div);
            var button = document.createElement(BUTTON_TAG);
            button.setAttribute(FORM_ATTRIBUTE, "tasks");
            button.setAttribute(FORMACTION_ATTRIBUTE, "UploadFileServlet");
            button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");
            button.innerHTML  = "Upload";
            button.setAttribute(FORMMETHOD_ATTRIBUTE, POST_METHOD);
            button.setAttribute(NAME_ATTRIBUTE, "file");
            fileName = currentTaskId + ":" + currentTask + ";" + "No file";
            div.appendChild(button);
        }
        label.setAttribute(VALUE_ATTRIBUTE, fileName);
        td.appendChild(label);


        td = document.createElement(TD_TAG);
        tr.appendChild(td);
        input = document.createElement(INPUT_TAG);
        input.setAttribute(TYPE_ATTRIBUTE, "checkbox");
        input.setAttribute(ID_ATTRIBUTE, "exampleCheck1");
        input.setAttribute(ONCLICK_ATTRIBUTE, "showDeleteButton('tasks','deleteConf')");
        input.setAttribute(VALUE_ATTRIBUTE, fileName);
        input.setAttribute(NAME_ATTRIBUTE, "deleteConferenceCheck");
        td.appendChild(input);
    }

    button = document.createElement(BUTTON_TAG);
    button.setAttribute(FORM_ATTRIBUTE, "tasks");
    button.setAttribute(FORMMETHOD_ATTRIBUTE, POST_METHOD);
    button.setAttribute(ID_ATTRIBUTE, "deleteConf");
    button.setAttribute(NAME_ATTRIBUTE, "typeLocation");
    button.setAttribute(VALUE_ATTRIBUTE, "basket");
    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger noneDisplay");
    if (isBasket) {
        button.setAttribute(FORMACTION_ATTRIBUTE, "DeleteConferenceServlet");
        button.innerHTML = "Delete";
        button.setAttribute(ONCLICK_ATTRIBUTE, "ajax-delete-conference()");
    }
    else {
        button.setAttribute(FORMACTION_ATTRIBUTE, "MoveConferenceServlet");
        button.innerHTML = "Move to basket";
        button.setAttribute(ONCLICK_ATTRIBUTE, "sendQueryToMoveConferenceServlet(value); return false;");
    }
    taskTable.appendChild(button);
}