var xmlHttpRequest = newXMLHttpRequest();

function sendQuery(method, responseHandler, servlet, query) {


    xmlHttpRequest.open(POST_METHOD, servlet, true);
    xmlHttpRequest.setRequestHeader(HEADER_CONTENT_TYPE,
        HEADER_TEXT);
    xmlHttpRequest.onreadystatechange = responseHandler;

    xmlHttpRequest.send("from=ajax" + query);
}

function formQuery() {
    var query = "&";
    for(var i = 0; i<arguments.length; i++) {
        var name = arguments[i++];
        var value = arguments[i];
        query += name + "=" + value + "&";
    }
    query = query.substring(0, query.length-1);
    return query;
}