function sendQueryToAddTaskServlet(taskType, taskName, description, date) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, POST_METHOD, printNewTask, "/AddTaskServlet",
        makeRequestBody(TASK_TYPE, taskType, TASK_NAME, taskName, DESCRIPTION, description, "date", date));
}

function printNewTask(jsonObject) {
    var taskType = jsonObject.taskType;

    var currentTaskType = document.getElementById("sectionName").getAttribute("name");

    var isBasket = taskType === BASKET;
    var isFixed = taskType === FIXED;
    var isMain = !isBasket && !isFixed;

    if(taskType === currentTaskType) {
        var task = jsonObject.task;
        printTask(task, isMain, isBasket, taskType);
    }
}