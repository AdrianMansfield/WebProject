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

    var section = document.getElementById("sectionName");

    if(section)  {

        var currentTaskType = section.getAttribute("name");

        if(taskType === currentTaskType) {

            var taskTable = document.getElementById("taskTable");

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