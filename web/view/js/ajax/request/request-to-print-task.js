function sendQueryToPrintTaskServlet(value) {

    var xmlHttpRequest = newXMLHttpRequest();

    sendRequest(xmlHttpRequest, GET_METHOD, drawTaskTable, PRINT_TASK_SERVLET, makeRequestBody(TASK_TYPE, value));

}