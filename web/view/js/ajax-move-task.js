function moveTaskResponseHandler() {
    if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
        var data = xmlHttpRequest.responseText;
        deleteTaskFromTable(JSON.parse(data));
    }
}

function sendQueryToMoveTaskServlet(value) {
    var checkBoxes = getCheckedCheckBoxes("deleteTaskCheck");
    var query = "";
    for(var i = 0; i<checkBoxes.length; i++)
        query += makeRequestBody("deleteTaskCheck", checkBoxes[i]);
    sendQuery(POST_METHOD,moveTaskResponseHandler,"/MoveTaskServlet",
        query + makeRequestBody("typeLocation", value));
}

function deleteTaskFromTable(jsonObject) {
    var taskId = jsonObject.taskId;

    var tr = document.getElementById(taskId);
    taskTable.removeChild(tr);

}