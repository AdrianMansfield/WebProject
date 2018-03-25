function dateTableData(taskId, date) {

    var td = document.createElement(TD_TAG);

    td.setAttribute(ID_ATTRIBUTE, taskId + "date");

    td.innerHTML = date;

    return td;
}