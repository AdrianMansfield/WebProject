

function changeFileName(jsonObject) {

    var taskName = jsonObject["taskName"];

    var fileName = jsonObject["fileName"];

    var fileElement = document.getElementById(taskName + ";" +  NO_FILE);

    fileElement.onclick = drawModalWindows.bind(this, fileName, taskName);

    fileElement.setAttribute(ID_ATTRIBUTE, taskName + ";" + fileName);

    fileElement.innerHTML = fileName;

}