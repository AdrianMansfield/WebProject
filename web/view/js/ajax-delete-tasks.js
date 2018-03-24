function sendRequestToDeleteTaskServlet(values) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, POST_METHOD, deleteTasksFromTable, "/DeleteTaskServlet", makeRequestBodyForArray(TASK_IDS, values));
}

function deleteTasksFromTable(jsonObject) {
    var taskIds = jsonObject["taskIds"];

    for(var i = 0; i<taskIds.length; i++) {
        removeTaskFromTable(taskIds[i]);
    }
}