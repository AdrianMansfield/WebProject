function removeTaskFromTable(taskId) {

    var taskTable = document.getElementById("taskTable");

    var tr = document.getElementsByName(taskId);

    taskTable.removeChild(tr[0]);

    taskTable.removeChild(tr[0]);

    if(taskTable.rows.length === 0) {

        removeAllElements(taskTable);

        var currentTaskType = document.getElementById("sectionName").getAttribute("name");
        if(currentTaskType === BASKET) {
            document.getElementById('tableContainer').removeChild(document.getElementById("taskDeleteButton"));
        }
    }




}