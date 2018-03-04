function sendQuery(method, servlet, query) {
    var xmlHttpRequest = newXMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function() {
        if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var data = xmlHttpRequest.responseText;
            printEventTable(JSON.parse(data));
        }
    };

    xmlHttpRequest.onreadystatechange();

    xmlHttpRequest.open(POST_METHOD, servlet, true);
    xmlHttpRequest.setRequestHeader(HEADER_CONTENT_TYPE,
        HEADER_TEXT);

    xmlHttpRequest.send("from=ajax" + query);
}

function sendQueryToPrintEventServlet(value) {
    sendQuery(POST_METHOD, "/PrintEventServlet", formQuery("conferenceId", value));
}


function printEventTable(jsonObject) {
    removeAllElements(eventTable);

    var eventList = jsonObject.events;
    for(var counter in eventList) {
        var id = eventList[counter].id;
        var name = eventList[counter].eventName;
        var time = eventList[counter].eventTime;

        var td = document.createElement(TD_TAG);
        td.innerHTML = id;
        eventTable.appendChild(td);

        td = document.createElement(TD_TAG);
        td.innerHTML = name;
        eventTable.appendChild(td);

        td = document.createElement(TD_TAG);
        td.innerHTML = time;
        eventTable.appendChild(td);

        td = document.createElement(TD_TAG);
        var input = document.createElement(INPUT_TAG);
        input.setAttribute(TYPE_ATTRIBUTE, "checkbox");
        input.setAttribute(CLASS_ATTRIBUTE, "form-check-input");
        input.setAttribute(NAME_ATTRIBUTE, "deleteEventCheck");
        input.setAttribute(ONCLICK_ATTRIBUTE, "showDeleteButton('events','deleteEvent')");
        td.appendChild(input);
        eventTable.appendChild(td);
    }
}
