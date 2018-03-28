function removeTaskFromTable(taskId) {

    var taskTable = document.getElementById(TASK_TABLE);

    var tr = document.getElementsByName(taskId);

    taskTable.removeChild(tr[0]);

    taskTable.removeChild(tr[0]);

    if(taskTable.rows.length === 0) {

        removeAllElements(taskTable);

        var currentTaskType = document.getElementById(SECTION_NAME).getAttribute(NAME_ATTRIBUTE);
        if(isBasket(currentTaskType)) {
            document.getElementById(TABLE_CONTAINER).removeChild(document.getElementById(TASK_DELETE_BUTTON));
        }
    }




}