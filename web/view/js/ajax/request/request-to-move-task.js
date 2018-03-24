function sendQueryToMoveTaskServlet(taskId, locationType) {
    var xmlHttpRequest = newXMLHttpRequest();

    sendRequest(xmlHttpRequest, POST_METHOD, eraseTask, MOVE_TASK_SERVLET, makeRequestBody(TASK_ID, taskId, LOCATION_TYPE, locationType));

}