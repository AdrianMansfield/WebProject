

function eraseTask(jsonObject) {

    var taskId = jsonObject["taskId"];

    removeTaskFromTable(taskId);
}