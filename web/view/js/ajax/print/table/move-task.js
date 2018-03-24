function moveTaskTableData(taskId, isMain) {

    var td = document.createElement(TD_TAG);

    td.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var locationType = FIXED;

    if (!isMain) {

        locationType = MAIN;

    }

    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(VALUE_ATTRIBUTE, "&#10004");

    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    button.onclick = sendQueryToMoveTaskServlet.bind(this, taskId, locationType);

    td.appendChild(button);

    return td;
}