function sendQueryToUploadFileServlet(taskName) {
    var xmlHttpRequest = newXMLHttpRequest();
    var formData = new FormData();
    var uploadFileForm = document.getElementById("uploadFile");
    var file = uploadFileForm.files[0];
    var ajax = "ajax";
    formData.append("from", ajax);
    formData.append("taskName", taskName);
    formData.append("file", file);
    sendRequest(xmlHttpRequest, FILE, changeFileName, UPLOAD_FILE_SERVLET, formData);
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