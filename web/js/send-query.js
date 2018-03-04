

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