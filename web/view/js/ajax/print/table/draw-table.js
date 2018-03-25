function drawTaskTable(jsonObject) {

    var taskTypeHeader = document.getElementById("taskTypeHeader");

    var taskTable = document.getElementById("taskTable");

    removeAllElements(taskTable);

    removeAllElements(taskTypeHeader);

    var tasks = jsonObject["tasks"];

    var taskType = jsonObject["taskType"];

    var isBasket = taskType === BASKET;

    var isFixed = taskType === FIXED;

    var isMain = !isBasket && !isFixed;

    var h5 = document.createElement(H5_TAG); //move that code to some suitable function !!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    h5.setAttribute(ID_ATTRIBUTE, "sectionName");

    h5.setAttribute(NAME_ATTRIBUTE, taskType);

    h5.innerHTML = taskType;

    taskTypeHeader.appendChild(h5);

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
    } // move this block of code to suitable file !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}


// delete this function  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
















