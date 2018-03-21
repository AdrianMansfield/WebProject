function removeTaskFromTable(taskId) {

    var taskTable = document.getElementById("taskTable");

    var tr = document.getElementsByName(taskId);

    for (var i = 0; i < tr.length; i++) {
        taskTable.removeChild(tr[i]);
    }
}