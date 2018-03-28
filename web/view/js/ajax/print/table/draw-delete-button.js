function drawDeleteButton(taskType, tasksLength) {

    if (document.getElementById(TASK_DELETE_BUTTON)){
        document.getElementById(TABLE_CONTAINER).removeChild(document.getElementById(TASK_DELETE_BUTTON));
    }
    if (isBasket(taskType) && tasksLength !== 0) {
        var tableContainer = document.getElementById(TABLE_CONTAINER);
        var button = document.createElement(BUTTON_TAG);
        button.setAttribute(FORM_ATTRIBUTE, DELETE);
        button.setAttribute(ID_ATTRIBUTE, TASK_DELETE_BUTTON);
        button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");
        button.innerHTML = DELETE;

        var event = function () {
            var values = getCheckedCheckBoxes(TASK_IDS);
            sendRequestToDeleteTaskServlet(values);
        };
        button.addEventListener(CLICK, event);
        tableContainer.appendChild(button);
    }
}