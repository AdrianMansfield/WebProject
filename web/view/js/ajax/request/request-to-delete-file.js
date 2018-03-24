function sendRequestToDeleteFileServlet() {
    var xmlHttpRequest = newXMLHttpRequest();
    sendRequest(xmlHttpRequest, POST_METHOD, changeFileName, "/DeleteFileServlet",
        makeRequestBody());
}