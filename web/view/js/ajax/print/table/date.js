function dateTableData(taskId, date) {

    var td = document.createElement(TD_TAG);

    td.setAttribute(ID_ATTRIBUTE, taskId + DATE);

    td.innerHTML = date;

    return td;
}