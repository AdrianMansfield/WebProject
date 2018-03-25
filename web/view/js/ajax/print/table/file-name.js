function fileNameTableData(taskId, fileName, taskName) {

    var td = document.createElement(TD_TAG);
    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(ID_ATTRIBUTE, taskName + ";" + fileName);

    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    button.onclick = drawModalWindows.bind(this, taskId, fileName, taskName);

    button.innerHTML = fileName;

    td.appendChild(button);

    return td;
}