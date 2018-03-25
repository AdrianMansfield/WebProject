function validateAddTaskForm(taskType, taskName, description, date) {
    var taskNameInput = document.forms["addTaskForm"]["taskName"];
    var taskDescriptionInput = document.forms["addTaskForm"]["description"];
    var taskDateInput = document.forms["addTaskForm"]["date"];
    var nameIsValid = validateNameField(taskName);
    var descriptionIsValid = validateDescriptionField(description);
    if (taskType === "SOMEDAY"){
        var dateIsValid = validateDateField(date);
    }
    if (!nameIsValid){
        taskNameInput.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
        taskDescriptionInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
        taskDateInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
    } else if (!descriptionIsValid){
        taskDescriptionInput.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
        taskNameInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
        taskDateInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
    } else if (!dateIsValid && taskType === "SOMEDAY"){
        taskDateInput.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
        taskDescriptionInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
        taskNameInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
    } else
        sendQueryToAddTaskServlet(taskType, taskName, description, date)

}

function validateChangeTaskInfoForm(taskId, attributeName) {
    var textarea = document.getElementById(TASK_ATTRIBUTE);
    var descriptionIsValid = validateDescriptionField(textarea.value);
    if (!descriptionIsValid){
        textarea.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
    } else {
        textarea.setAttribute(CLASS_ATTRIBUTE, "form-control");
        sendRequestToChangeTaskInfo(taskId, attributeName);
        if (attributeName === 'date'){
            document.location.href = "#";
        }
        removeAllElements(document.getElementById(FILE_MODAL_WINDOW));

    }
}

function validateNameField(taskName) {
    if (taskName === "") {
        return false;
    } else
        return true;

}

function validateDescriptionField(description) {
    if (description === "") {
        return false;
    } else
        return true;

}

function validateDateField(date) {
    var regExp = /(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}/;
    var isSomeday = document.getElementById("someday").checked;
    if (isSomeday && date === "" || !regExp.test(date)) {
        return false;
    }
        return true;
}

