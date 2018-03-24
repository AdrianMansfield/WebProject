

function changeFileName(jsonObject) {

    var taskName = jsonObject["taskName"];

    var newFileName = jsonObject["newFileName"];

    var oldFileName = jsonObject["oldFileName"];

    var fileElement = document.getElementById(taskName + ";" +  oldFileName);

    fileElement.onclick = drawModalWindows.bind(this, newFileName, taskName);

    fileElement.setAttribute(ID_ATTRIBUTE, taskName + ";" + newFileName);

    fileElement.innerHTML = fileName;

}