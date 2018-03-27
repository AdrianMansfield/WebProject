function drawTaskTable(jsonObject) {

    var taskTypeHeader = document.getElementById("taskTypeHeader");

    var taskTable = document.getElementById("taskTable");

    removeAllElements(taskTable);

    removeAllElements(taskTypeHeader);

    var tasks = jsonObject["tasks"];

    var taskType = jsonObject["taskType"];

    // var isBasket = taskType === BASKET;
    //
    // var isFixed = taskType === FIXED;

    var isMain = !isBasket(taskType) && !isFixed(taskType);

    var h5 = document.createElement(H5_TAG);

    h5.setAttribute(ID_ATTRIBUTE, "sectionName");

    h5.setAttribute(NAME_ATTRIBUTE, taskType);

    h5.innerHTML = taskType;

    taskTypeHeader.appendChild(h5);

    if(tasks.length !== 0){
        var thead = tableHeader(taskType);

        taskTable.appendChild(thead);
    }

    for (var counter in tasks) {

        printTask(tasks[counter], isMain, isBasket(taskType), taskType);

    }

    drawDeleteButton(taskType, tasks.length);
}


















