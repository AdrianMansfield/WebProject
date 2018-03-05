function moveConferenceResponseHandler() {
    if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
        var data = xmlHttpRequest.responseText;
        deleteConferenceFromTable(JSON.parse(data));
    }
}

function sendQueryToMoveConferenceServlet(value) {
    var checkBoxes = getCheckedCheckBoxes("deleteConferenceCheck");
    var query = "";
    for(var i = 0; i<checkBoxes.length; i++)
        query += formQuery("deleteConferenceCheck", checkBoxes[i]);
    sendQuery(POST_METHOD,moveConferenceResponseHandler,"/MoveConferenceServlet",
        query + formQuery("typeLocation", value));
}

function deleteConferenceFromTable(jsonObject) {
    var conferenceIds = jsonObject.conferenceIds;
    for(var counter in conferenceIds) {
        var tr = document.getElementById(conferenceIds[counter].id);
        taskTable.removeChild(tr);
    }
}