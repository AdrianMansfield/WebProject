function sendRequestToDeleteTaskServlet(values) {

    var xmlHttpRequest = newXMLHttpRequest();

    sendRequest(xmlHttpRequest, POST_METHOD, deleteTasksFromTable, "/DeleteTaskServlet", makeRequestBodyForArray(TASK_IDS, values));

}