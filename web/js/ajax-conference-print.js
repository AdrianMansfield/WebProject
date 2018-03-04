function sendQuery(method, servlet, query) {
    var xmlHttpRequest = newXMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var data = xmlHttpRequest.responseText;
            printTaskTable(JSON.parse(data));
        }
    };

    xmlHttpRequest.onreadystatechange();

    xmlHttpRequest.open(POST_METHOD, servlet, true);
    xmlHttpRequest.setRequestHeader(HEADER_CONTENT_TYPE,
        HEADER_TEXT);

    xmlHttpRequest.send("from=ajax" + query);
}

function sendQueryToPrintConferenceServlet(value) {
    sendQuery(POST_METHOD, "/PrintConferenceServlet", formQuery("dateType", value));
}




function getMapFromJson(map) {
    var objectMap = {};
    for(var i in map) {
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
    var thead = document.createElement("thead");
    taskTable.appendChild(thead);
    var th = document.createElement("th");
    th.innerHTML = "name";
    thead.appendChild(th);
    var th = document.createElement("th");
    th.innerHTML = "file";
    thead.appendChild(th);
    var th = document.createElement("th");
    input = document.createElement("input");
    input.setAttribute("type", "checkbox");
    input.setAttribute("id","checkAll");
    input.setAttribute("onclick","addStatement('checkAll','tasks'); showDeleteButton('tasks','deleteConf')");
    th.appendChild(input);
    var label = document.createElement("label");
    label.setAttribute("for","checkAll");
    th.appendChild(label);
    thead.appendChild(th);
    var tbody = document.createElement("tbody");
    taskTable.appendChild(tbody);
    for(var counter in conferenceList) {
        var currentTaskId = conferenceList[counter].id;
        var currentTask = conferenceList[counter].taskName;
        var tr = document.createElement(TR_TAG);
        taskTable.appendChild(tr);
        var td = document.createElement(TD_TAG);
        td.setAttribute("width","100px");
        tr.appendChild(td);
        var input = document.createElement(INPUT_TAG);
        input.setAttribute(NAME_ATTRIBUTE, "currentTask");
        input.setAttribute(TYPE_ATTRIBUTE, "radio");
        input.setAttribute(CLASS_ATTRIBUTE, "invisible-circle-label");
        input.setAttribute(VALUE_ATTRIBUTE, currentTaskId);
        input.setAttribute(ID_ATTRIBUTE, currentTaskId);
        td.appendChild(input);
        var label = document.createElement(LABEL_TAG);
        label.setAttribute(FOR_ATTRIBUTE, currentTaskId);
        label.innerHTML = currentTask;
        td.appendChild(label);
        td = document.createElement(TD_TAG);
        td.setAttribute("width","380px");
        tr.appendChild(td);
        var fileName = fileMap[currentTask];


        var button = document.createElement(BUTTON_TAG);
        button.setAttribute(FORM_ATTRIBUTE, "tasks");
        button.setAttribute(FORMACTION_ATTRIBUTE, "DownloadFileServlet");
        button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");
        button.setAttribute(FORMMETHOD_ATTRIBUTE, POST_METHOD);
        button.setAttribute(NAME_ATTRIBUTE, "file");
        if(fileName) {
            button.innerHTML = fileName;
            fileName = currentTaskId + ":" + currentTask + ";" + fileName;
        }
        else {
            fileName  = currentTaskId + ":" + currentTask + ";" + "No file";
            button.innerHTML = "No file";
            button.setAttribute("disabled", "disabled");
        }
        button.setAttribute(VALUE_ATTRIBUTE,  fileName);
        td.appendChild(button);
        td = document.createElement(TD_TAG);
        tr.appendChild(td);
        input = document.createElement(INPUT_TAG);
        input.setAttribute(TYPE_ATTRIBUTE, "checkbox");
        input.setAttribute(ID_ATTRIBUTE,"exampleCheck1");
        input.setAttribute(ONCLICK_ATTRIBUTE,"showDeleteButton('tasks','deleteConf')");
        input.setAttribute(VALUE_ATTRIBUTE, fileName);
        input.setAttribute(NAME_ATTRIBUTE, "deleteConferenceCheck");
        td.appendChild(input);
    }

    button = document.createElement(BUTTON_TAG);
    button.setAttribute(FORM_ATTRIBUTE, "tasks");
    button.setAttribute(FORMMETHOD_ATTRIBUTE, POST_METHOD);
    button.setAttribute(ID_ATTRIBUTE,"deleteConf");
    button.setAttribute(NAME_ATTRIBUTE, "typeLocation");
    button.setAttribute(VALUE_ATTRIBUTE, "basket");
    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger noneDisplay");
    if(isBasket) {
        button.setAttribute(FORMACTION_ATTRIBUTE,"DeleteConferenceServlet");
        button.innerHTML = "Delete";
    }
    else {
        button.setAttribute(FORMACTION_ATTRIBUTE,"MoveConferenceServlet");
        button.innerHTML = "Move to basket";
    }
    taskTable.appendChild(button);
}