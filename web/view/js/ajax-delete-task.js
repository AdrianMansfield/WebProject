function sendQueryToMoveTaskServlet(taskId, locationType) {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, POST_METHOD, removeTaskFromTable, MOVE_TASK_SERVLET, makeRequestBody(TASK_ID, taskId, LOCATION_TYPE, locationType));
    //xmlHttpRequest.close();
    return false;
}

function removeTaskFromTable(jsonObject) {
    var taskId = jsonObject.taskId;

    var taskTable = document.getElementById("taskTable");

    var div = document.getElementById(taskId);

    //div.innerHTML = "";

    while(div.firstChild) {
        div.removeChild(div.firstChild);
    }

    taskTable.removeChild(div);

}