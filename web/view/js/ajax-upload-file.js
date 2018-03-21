function sendQueryToUploadFileServlet(taskName) {

    var xmlHttpRequest = newXMLHttpRequest();

    var uploadFileForm = document.getElementById("uploadFile");

    var file = uploadFileForm.files[0];

    sendRequest(xmlHttpRequest, FILE, changeFileName, UPLOAD_FILE_SERVLET, makeFormData(FROM_NAME_PARAMETER,
        FROM_VALUE_PARAMETER, TASK_NAME, taskName, FILE, file));

    //xmlHttpRequest.close();

    return false;
}

function changeFileName(jsonObject) {

    var taskName = jsonObject.taskName;

    var fileName = jsonObject.fileName;

    var fileElement = document.getElementById(taskName + ";" +  NO_FILE);

    fileElement.onclick = drawModalWindows.bind(this, fileName, taskName);

    fileElement.setAttribute(ID_ATTRIBUTE, taskName + ";" + fileName);

    fileElement.innerHTML = fileName;

}