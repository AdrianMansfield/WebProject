function newXMLHttpRequest() {

    var xmlreq = false;

    if (window.XMLHttpRequest) {

        // Создадим XMLHttpRequest объект для не-Microsoft браузеров
        xmlreq = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        // Создадим XMLHttpRequest с помощью MS ActiveX
        try {
            // Попробуем создать XMLHttpRequest для поздних версий
            // Internet Explorer

            xmlreq = new ActiveXObject("Msxml2.XMLHTTP");

        } catch (e1) {

            // Не удалось создать требуемый ActiveXObject

            try {
                // Пробуем вариант, который поддержат более старые версии
                //  Internet Explorer

                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

            } catch (e2) {

                // Не в состоянии создать XMLHttpRequest с помощью ActiveX
            }
        }
    }

    return xmlreq;
}
var m = 0;
function sendQueryToMainServlet(value) {
    var xmlHttpRequest = newXMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function () {
        if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var data = xmlHttpRequest.responseText;
            printTaskTable(getObjectFromJson(data));
        }
    };

    xmlHttpRequest.onreadystatechange();

    xmlHttpRequest.open("POST", "PrintConferenceServlet", true);
    xmlHttpRequest.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");


    xmlHttpRequest.send("from=ajax&dateType=" + value);
}

function removeAllElements() {
    taskTable.innerHTML = "";
}

function getObjectFromJson(text) {
    return JSON.parse(text);
}

function getMapFromJson(map) {
    var objectMap = {};
    for(var i in map) {
        objectMap[map[i].taskName] = map[i].fileName;
    }
    return objectMap;
}


function printTaskTable(jsonObject) {
    removeAllElements();

    var taskList = jsonObject.taskList;

    var fileMap = getMapFromJson(jsonObject.fileMap);

    drawTaskTable(taskList, fileMap);

}

function drawTaskTable(taskList, fileMap) {
    for(var counter in taskList) {
        var currentTaskId = taskList[counter].id;
        var currentTask = taskList[counter].taskName;
        var tr = document.createElement("tr");
        taskTable.appendChild(tr);
        var td = document.createElement("td");
        tr.appendChild(td);
        var input = document.createElement("input");
        input.setAttribute("name", "currentTask");
        input.setAttribute("type", "radio");
        input.setAttribute("class", "invisible-circle-label");
        input.setAttribute("value", currentTaskId);
        input.setAttribute("id", currentTaskId);
        td.appendChild(input);
        var label = document.createElement("label");
        label.setAttribute("for", currentTaskId);
        label.innerHTML = currentTask;
        td.appendChild(label);
        td = document.createElement("td");
        tr.appendChild(td);
        var fileName = fileMap[currentTask];

        if(fileName) {
            var button = document.createElement("button");
            button.setAttribute("form", "tasks");
            button.setAttribute("formaction", "DownloadFileServlet");
            button.setAttribute("class", "btn btn-outline-danger");
            button.setAttribute("formmethod", "post");
            button.setAttribute("name", "file");
            button.setAttribute("value", currentTask + ";" + fileName);
            button.innerHTML = fileName;
            td.appendChild(button);
        }
        td = document.createElement("td");
        tr.appendChild(td);
        input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        input.setAttribute("class","form-check-input");
        input.setAttribute("id","exampleCheck1");
        input.innerHTML = "label";
        td.appendChild(input);
    }
}