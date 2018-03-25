function sendQueryToAddTaskServlet(taskType, taskName, description, date) {
    var xmlHttpRequest = newXMLHttpRequest();

    sendRequest(xmlHttpRequest, POST_METHOD, printNewTask, "/AddTaskServlet",
        makeRequestBody(TASK_TYPE, taskType, TASK_NAME, taskName, DESCRIPTION, description, "date", date));
}