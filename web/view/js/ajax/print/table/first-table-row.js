function firstTableRow(taskId, taskName, fileName, isMain, isBasket, taskType, date) {

    var tr = document.createElement(TR_TAG);

    var td = moveTaskTableData(taskId, isMain, taskType);

    tr.appendChild(td);

    td = taskNameTableData(taskName, taskId);

    tr.appendChild(td);

    td = fileNameTableData(taskId, fileName, taskName);

    tr.appendChild(td);

    if (taskType === "SOMEDAY") {

        td = dateTableData(taskId, date);

        tr.appendChild(td);
    }

    td = throwTaskTableData(taskId, isBasket);

    tr.appendChild(td);

    return tr;
}