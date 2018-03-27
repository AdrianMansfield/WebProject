function sendRequestToUploadFileServlet(taskId, taskName) {

    var xmlHttpRequest = newXMLHttpRequest();

    var uploadFile = document.getElementById("uploadFile");

    var file = uploadFile.files[0];

    sendRequest(xmlHttpRequest, FILE, changeFileName, UPLOAD_FILE_SERVLET, makeFormData(FROM_NAME_PARAMETER,
        FROM_VALUE_PARAMETER, TASK_NAME, taskName, FILE, file, TASK_ID, taskId));

}