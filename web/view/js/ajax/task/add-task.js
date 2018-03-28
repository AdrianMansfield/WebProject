function hideDateField() {
    var taskDateInput = document.getElementById('dateField');
    taskDateInput.setAttribute(CLASS_ATTRIBUTE,"noneDisplay");
}

function showDateField() {
    var taskDateInput = document.getElementById('dateField');
    taskDateInput.setAttribute(CLASS_ATTRIBUTE,"displayElement");
}

function printNewTask(jsonObject) {

    var taskType = jsonObject["taskType"];

    var section = document.getElementById(SECTION_NAME);

    if(section)  {

        var currentTaskType = section.getAttribute(NAME_ATTRIBUTE);

        if(taskType === currentTaskType) {

            var taskTable = document.getElementById(TASK_TABLE);

            if (!taskTable.firstChild) {

                var th = tableHeader(taskType);

                taskTable.appendChild(th);

            }


            var isMain = !isBasket(taskType) && !isFixed(taskType);

            var task = jsonObject["task"];

            printTask(task, isMain, isBasket(taskType), taskType);
            return;
        }
    }
    sendQueryToPrintTaskServlet(taskType);

}