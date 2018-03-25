function hideDateField() {
    var taskDateInput = document.getElementById('dateField');
    taskDateInput.setAttribute(CLASS_ATTRIBUTE,"noneDisplay");
}

function showDateField() {
    var taskDateInput = document.getElementById('dateField');
    taskDateInput.setAttribute(CLASS_ATTRIBUTE,"displayElement");
}

function printNewTask(jsonObject) {
    var taskType = jsonObject.taskType;
    alert("hiiiiiiiii")

    var taskTable = document.getElementById("taskTable");
    if (taskType.firstChild){
        var currentTaskType = document.getElementById("sectionName").getAttribute("name");

        var isBasket = taskType === BASKET;
        var isFixed = taskType === FIXED;
        var isMain = !isBasket && !isFixed;

        if(taskType === currentTaskType) {
            var task = jsonObject.task;
            printTask(task, isMain, isBasket, taskType);
        }
    }
}