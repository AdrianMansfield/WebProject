function drawDeleteButton(taskType, tasksLength) {
    if (document.getElementById("taskDeleteButton")){
        document.getElementById('tableContainer').removeChild(document.getElementById("taskDeleteButton"));
    }
    if (taskType === BASKET && tasksLength !== 0) {
        var tableContainer = document.getElementById('tableContainer');
        var button = document.createElement(BUTTON_TAG);
        button.setAttribute(FORM_ATTRIBUTE, DELETE);
        button.setAttribute(ID_ATTRIBUTE, "taskDeleteButton");
        button.innerHTML = DELETE;

        var event = function () {
            var values = getCheckedCheckBoxes(TASK_IDS);
            sendRequestToDeleteTaskServlet(values);
        };
        button.addEventListener("click", event);
        tableContainer.appendChild(button);
    }
}