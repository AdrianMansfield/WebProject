function deleteTasksFromTable(jsonObject) {
    var taskIds = jsonObject["taskIds"];

    for(var i = 0; i<taskIds.length; i++) {
        removeTaskFromTable(taskIds[i]);
    }
}