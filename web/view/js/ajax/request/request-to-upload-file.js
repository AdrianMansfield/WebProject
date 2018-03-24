function sendRequestToUploadFileServlet(taskName) {

    var xmlHttpRequest = newXMLHttpRequest();

    var uploadFileForm = document.getElementById("uploadFile");

    var file = uploadFileForm.files[0];

    sendRequest(xmlHttpRequest, FILE, changeFileName, UPLOAD_FILE_SERVLET, makeFormData(FROM_NAME_PARAMETER,
        FROM_VALUE_PARAMETER, TASK_NAME, taskName, FILE, file));

}