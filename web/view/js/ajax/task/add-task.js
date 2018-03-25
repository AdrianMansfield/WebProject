function hideDateField() {
    var taskDateInput = document.getElementById('dateField');
    taskDateInput.setAttribute(CLASS_ATTRIBUTE,"noneDisplay");
}

function showDateField() {
    var taskDateInput = document.getElementById('dateField');
    taskDateInput.setAttribute(CLASS_ATTRIBUTE,"displayElement");
}

function printNewTask(jsonObject) {

    var taskTable = document.getElementById("taskTable");

    if (taskTable.firstChild){

        var taskType = jsonObject["taskType"];

        var currentTaskType = document.getElementById("sectionName").getAttribute("name");

        if(taskType === currentTaskType) {


            var isBasket = taskType === BASKET;
            var isFixed = taskType === FIXED;
            var isMain = !isBasket && !isFixed;

            var task = jsonObject["task"];

            printTask(task, isMain, isBasket, taskType);

        }
    }
}