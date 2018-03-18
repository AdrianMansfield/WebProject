var requestMethods = {};

requestMethods[POST_METHOD] = sendPostRequest;

requestMethods[GET_METHOD] = sendGetRequest;

function sendRequest(xmlHttpRequest, method, receivingFunction, servletUrl, query) {


    xmlHttpRequest.onreadystatechange = function() {
        if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var data = xmlHttpRequest.responseText;
            receivingFunction(JSON.parse(data));
        }
    };

    requestMethods[method](xmlHttpRequest, servletUrl, query);
}

function sendGetRequest(xmlHttpRequest, servletUrl, query) {

    xmlHttpRequest.open(GET_METHOD, servletUrl + QUESTION_MARK + query, true);

    xmlHttpRequest.setRequestHeader(HEADER_CONTENT_TYPE,
        HEADER_TEXT);

    xmlHttpRequest.send();

}

function sendPostRequest(xmlHttpRequest, servletUrl, query) {

    xmlHttpRequest.open(POST_METHOD, servletUrl, true);

    xmlHttpRequest.setRequestHeader(HEADER_CONTENT_TYPE,
        HEADER_TEXT);

    xmlHttpRequest.send(query);

}



function makeRequestBody() {

    var query = FROM_NAME_PARAMETER + EQUAL_SIGN + FROM_VALUE_PARAMETER;
    for(var i = 0; i<arguments.length; i++) {
        var name = arguments[i++];
        var value = arguments[i];
        query += AMPERSAND_CHARACTER + name + EQUAL_SIGN + encodeURIComponent(value);
    }

    return query;
}