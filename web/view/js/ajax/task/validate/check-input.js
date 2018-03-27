function isEmptyString (taskAttribute){
    return taskAttribute === "";
}

function validateDateField (date){
    var regExp = /(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}/;
    var isSomeday = document.getElementById("someday").checked;
    return isSomeday && isEmptyString(date)&& !regExp.test(date);
}