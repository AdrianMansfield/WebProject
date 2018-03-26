function sendRequestToDeleteFileServlet(taskId) {

    var xmlHttpRequest = newXMLHttpRequest();

    sendRequest(xmlHttpRequest, POST_METHOD, changeFileName, "/DeleteFileServlet",
        makeRequestBody(TASK_ID, taskId));

}