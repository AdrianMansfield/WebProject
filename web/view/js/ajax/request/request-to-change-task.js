function sendRequestToChangeTaskInfo(taskId,  infoType) {
    var xmlHttpRequest = newXMLHttpRequest();
    var taskAttribute = document.getElementById(TASK_ATTRIBUTE).value;

    sendRequest(xmlHttpRequest, POST_METHOD, changeAttribute, "/ChangeTaskServlet",
        makeRequestBody(TASK_ID, taskId, TASK_ATTRIBUTE, taskAttribute, INFO_TYPE, infoType));
}