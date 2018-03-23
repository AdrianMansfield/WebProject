function drawChangeInfoModalWindow(attributeName, taskId, taskInfo) {

    removeAllElements(document.getElementById("fileModalWindow"));
    drawChangeNameWindow(attributeName, taskId, taskInfo);
}


function drawChangeNameWindow(attributeName, taskId, taskInfo) {
    var aside = document.createElement(ASIDE_TAG);
    var fileModalWindow = document.getElementById(FILE_MODAL_WINDOW);

    aside.setAttribute(CLASS_ATTRIBUTE,JS_MODAL_WINDOW);
    var header = document.createElement(HEADER_TAG);
    var h2 = document.createElement(H2_TAG);

    h2.innerHTML = "Change " + attributeName;

    header.appendChild(h2);
    aside.appendChild(header);

    var section = document.createElement(SECTION_TAG);
    var form = document.createElement("form");

    form.setAttribute(ACTION_ATTRIBUTE, CHANGE_TASK_INFO_SERVLET);

    form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, TASK_ID);

    input.setAttribute(VALUE_ATTRIBUTE, taskId);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, INFO_TYPE);

    input.setAttribute(VALUE_ATTRIBUTE, attributeName);

    form.appendChild(input);

    var label = document.createElement(LABEL_TAG);

    label.innerHTML = "Enter new " + attributeName;

    form.appendChild(label);

    var textarea = document.createElement(TEXTAREA_TAG);

    textarea.setAttribute(NAME_ATTRIBUTE, TASK_ATTRIBUTE);

    textarea.setAttribute(CLASS_ATTRIBUTE,"form-control");

    textarea.innerHTML = taskInfo;

    form.appendChild(textarea);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, SUBMIT_ATTRIBUTE);

    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-sm btn-outline-danger");

    input.setAttribute(VALUE_ATTRIBUTE,"Change");

    form.appendChild(input);

    section.appendChild(form);
    aside.appendChild(section);

    var button = document.createElement(BUTTON_TAG);
    button.setAttribute(CLASS_ATTRIBUTE,"btn btn-sm btn-outline-danger");
    button.innerHTML = "close";

    button.onclick = function () {
        removeAllElements(fileModalWindow);
    };

    var footer = document.createElement(FOOTER_TAG);

    footer.setAttribute(CLASS_ATTRIBUTE, FOOTER_TAG);
    footer.appendChild(button);
    aside.appendChild(footer);
    fileModalWindow.appendChild(aside);
}

