function printTask(task, isMain, isBasket, taskType) {
    var taskTable = document.getElementById("taskTable");

    var taskId = task["taskId"];

    var taskName = task["taskName"];

    var description = task["description"];

    var fileName = task["fileName"];

    var date = task.date;

    var tr = firstTableRow(taskId, taskName, fileName, isMain, isBasket, taskType, date);

    tr.setAttribute(NAME_ATTRIBUTE, taskId);

    taskTable.appendChild(tr);

    tr = descriptionSecondTableRow(taskName, description, date,  taskId);

    tr.setAttribute(NAME_ATTRIBUTE, taskId);

    taskTable.appendChild(tr);

}