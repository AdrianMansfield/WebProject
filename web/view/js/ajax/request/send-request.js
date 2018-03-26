var requestMethods = {};

requestMethods[POST_METHOD] = sendPostRequest;

requestMethods[GET_METHOD] = sendGetRequest;

requestMethods[FILE] = sendPostWithFileRequest;

function sendRequest(xmlHttpRequest, method, callBackFunction, servletUrl, query) {

    xmlHttpRequest.onreadystatechange = function() {

        if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {

            //var regExp = /(\\{.*\\})+/;

            var data = xmlHttpRequest.responseText;


            var jsonObject = JSON.parse(data);

            callBackFunction(jsonObject);



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


function sendPostWithFileRequest(xmlHttpRequest, servletUrl, query) {

    xmlHttpRequest.open(POST_METHOD, servletUrl, true);

    xmlHttpRequest.send(query);

}



function makeRequestBody() {

    var query = FROM_NAME_PARAMETER + EQUAL_SIGN + encodeURIComponent(FROM_VALUE_PARAMETER);

    for(var i = 0; i<arguments.length; i++) {
        var name = arguments[i++];
        var value = arguments[i];
        query += AMPERSAND_CHARACTER + name + EQUAL_SIGN + encodeURIComponent(value);
    }

    return query;
}


function makeFormData() {

    var formData = new FormData();

    formData.append(FROM_NAME_PARAMETER, FROM_VALUE_PARAMETER);

    for(var i = 0; i<arguments.length; i++) {
        var name = arguments[i++];
        var value = arguments[i];
        formData.append(name, value);
    }

    return formData;
}

function makeRequestBodyForArray(name, values) {
    var query = FROM_NAME_PARAMETER + EQUAL_SIGN + encodeURIComponent(FROM_VALUE_PARAMETER);

    for(var i = 0; i<values.length; i++) {
        query += AMPERSAND_CHARACTER + name + EQUAL_SIGN + encodeURIComponent(values[i]);
    }

    return query;

}