function sendQueryToMoveTaskServlet(taskId, locationType) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, POST_METHOD, removeTaskFromTable, MOVE_TASK_SERVLET, makeRequestBody(TASK_ID, taskId, LOCATION_TYPE, locationType));
//xmlHttpRequest.close();
    return false;
}

function removeTaskFromTable(jsonObject) {
    var taskId = jsonObject.taskId;

    var taskTable = document.getElementById("taskTable");

    var tr = document.getElementsByName(taskId);

    for(var i = 0; i<tr.length; i++) {
        taskTable.removeChild(tr[i]);
    }

}