function sendQueryToUploadFileServlet(taskName) {
    var xmlHttpRequest = newXMLHttpRequest();
    var formData = new FormData();
    var uploadFileForm = document.getElementById("uploadFile");
    var file = uploadFileForm.files[0];
    formData.append("taskName", taskName);
    formData.append("file", file);
    sendRequest(xmlHttpRequest, POST_METHOD, removeTaskFromTable, UPLOAD_FILE_SERVLET, formData);
    //xmlHttpRequest.close();
    return false;
}

function changeFileName(jsonObject) {

    var taskName = jsonObject.taskname;

    var fileName = jsonObject.fileName;

    var fileElement = document.getElementById(taskName + ";" + fileName);



}