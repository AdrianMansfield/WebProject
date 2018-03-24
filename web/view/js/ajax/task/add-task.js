

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