function descriptionSecondTableRow(taskName, description, date,  taskId) {

    var tr = document.createElement(TR_TAG);

    tr.setAttribute(ID_ATTRIBUTE, taskName);

    tr.setAttribute(CLASS_ATTRIBUTE, MODAL_DESCRIPTION);

    var td = document.createElement(TD_TAG);

    td.setAttribute(ID_ATTRIBUTE, taskId + INFO);

    td.setAttribute(COLSPAN_ATTRIBUTE, 5);

    var h5 = document.createElement(H5_TAG);

    h5.innerHTML = DESCRIPTION;

    td.appendChild(h5);

    var p = document.createElement(P_TAG);

    p.setAttribute(ID_ATTRIBUTE,taskId +DESCRIPTION);

    p.innerHTML = description;

    td.appendChild(p);

    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,DESCRIPTION);

    button.setAttribute(CLASS_ATTRIBUTE,"btn mr-3");

    button.innerHTML = CHANGE_DESCRIPTION_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this,DESCRIPTION,taskId,description);

    td.appendChild(button);

    button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,DATE);

    button.setAttribute(CLASS_ATTRIBUTE,"btn mr-3");

    button.innerHTML = CHANGE_DATE_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this,DATE, taskId, date);

    td.appendChild(button);

    var a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = CLOSE_HREF;

    td.appendChild(a);

    tr.appendChild(td);

    return tr;
}