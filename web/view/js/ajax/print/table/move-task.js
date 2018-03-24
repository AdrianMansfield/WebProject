function moveTaskTableData(taskId, isMain, taskType) {

    var td = document.createElement(TD_TAG);

    var form = document.createElement(FORM_ATTRIBUTE);

    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var locationType = FIXED;

    if (isMain) {
        locationType = FIXED.toLowerCase();
    }

    else {
        locationType = MAIN;
    }

    var input = document.createElement(BUTTON_TAG);

    input.setAttribute(VALUE_ATTRIBUTE, "&#10004");

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    input.onclick = sendQueryToMoveTaskServlet.bind(this, taskId, locationType);

    form.appendChild(input);

    td.appendChild(form);

    return td;
}