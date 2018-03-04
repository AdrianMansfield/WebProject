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
    sendQuery(POST_METHOD, "/PrintEventServlet", formQuery("dateType", value));
}


function printEventTable(jsonObject) {

}