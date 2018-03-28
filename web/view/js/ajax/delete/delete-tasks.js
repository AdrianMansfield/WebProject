function deleteTasksFromTable(jsonObject) {
    var taskIds = jsonObject[TASK_IDS];

    for(var i = 0; i<taskIds.length; i++) {
        removeTaskFromTable(taskIds[i]);
    }
}