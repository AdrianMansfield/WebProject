function descriptionSecondTableRow(taskName, description, date,  taskId) {

    var tr = document.createElement(TR_TAG);

    tr.setAttribute(ID_ATTRIBUTE, taskName);

    tr.setAttribute(CLASS_ATTRIBUTE, MODAL_DESCRIPTION);

    var td = document.createElement(TD_TAG);

    td.setAttribute(ID_ATTRIBUTE, taskId + "info");

    td.setAttribute(COLSPAN_ATTRIBUTE, 5);

    var h5 = document.createElement(H5_TAG);

    h5.innerHTML = "description";

    td.appendChild(h5);

    var p = document.createElement("p");

    p.setAttribute(ID_ATTRIBUTE,taskId +'description');

    p.innerHTML = description;

    td.appendChild(p);

    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,'description');

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger mr-3");

    button.innerHTML = CHANGE_DESCRIPTION_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this,'description',taskId,description);

    td.appendChild(button);

    button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,'name');

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger mr-3");

    button.innerHTML = CHANGE_NAME_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this, 'name', taskId, taskName);

    td.appendChild(button);

    button = document.createElement(BUTTON_TAG);

    button.setAttribute(NAME_ATTRIBUTE,'date');

    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger mr-3");

    button.innerHTML = CHANGE_DATE_HREF;

    button.onclick = drawChangeInfoModalWindow.bind(this, 'date', taskId, date);

    td.appendChild(button);

    var a = document.createElement(A_TAG);

    a.setAttribute(HREF_ATTRIBUTE, "#");

    a.setAttribute(CLASS_ATTRIBUTE, "btn");

    a.innerHTML = CLOSE_HREF;

    td.appendChild(a);

    tr.appendChild(td);

    return tr;
}