

function changeFileName(jsonObject) {

    var taskId = jsonObject["taskId"];

    var taskName = jsonObject["taskName"];

    var newFileName = jsonObject["newFileName"];

    var oldFileName = jsonObject["oldFileName"];

    var fileElement = document.getElementById(taskName + ";" +  oldFileName);

    alert(fileElement);

    fileElement.onclick = drawModalWindows.bind(this, taskId, newFileName, taskName);

    fileElement.setAttribute(ID_ATTRIBUTE, taskName + ";" + newFileName);

    fileElement.innerHTML = newFileName;

}