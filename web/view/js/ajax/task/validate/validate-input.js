function validateAddTaskForm(taskType, taskName, description, date) {
    var taskNameInput = document.forms["addTaskForm"]["taskName"];
    var taskDescriptionInput = document.forms["addTaskForm"]["description"];
    var taskDateInput = document.forms["addTaskForm"]["date"];
    taskNameInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
    taskDescriptionInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
    taskDateInput.setAttribute(CLASS_ATTRIBUTE, "form-control");
    if (taskType === "someday"){
        var dateIsValid = validateDateField(date);
    }
    if (isEmptyString(taskName)){
        taskNameInput.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
    } else if (isEmptyString(description)){
        taskDescriptionInput.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
    } else if (dateIsValid && taskType === "someday"){
        taskDateInput.setAttribute(CLASS_ATTRIBUTE, "error-form-control");
    } else{
        sendQueryToAddTaskServlet(taskType, taskName, description, date);
        document.forms["addTaskForm"]["taskName"].value = '';
        document.forms["addTaskForm"]["description"].value = '';
        document.forms["addTaskForm"]["date"].value = '';
    }
}

function validateChangeTaskInfoForm(taskId, attributeName) {
    var textarea = document.getElementById(TASK_ATTRIBUTE);

    if (isEmptyString(textarea.value)){
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


