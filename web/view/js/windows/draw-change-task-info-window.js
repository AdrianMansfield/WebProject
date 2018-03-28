function drawChangeInfoModalWindow(attributeName, taskId, taskInfo) {

    removeAllElements(document.getElementById(FILE_MODAL_WINDOW));
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

    var label = document.createElement(LABEL_TAG);

    label.innerHTML = "Enter new " + attributeName;

    section.appendChild(label);

    var textarea = document.createElement(TEXTAREA_TAG);

    textarea.setAttribute(NAME_ATTRIBUTE, TASK_ATTRIBUTE);

    textarea.setAttribute(ID_ATTRIBUTE, TASK_ATTRIBUTE);

    textarea.setAttribute(CLASS_ATTRIBUTE,FORM_CONTROL);

    textarea.innerHTML = taskInfo;

    section.appendChild(textarea);

    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-sm btn-outline-danger");

    button.innerHTML = "Change";

    var event = function() {
        validateChangeTaskInfoForm(taskId,attributeName);
    };

    button.addEventListener(CLICK, event);

    section.appendChild(button);

    aside.appendChild(section);

    button = document.createElement(BUTTON_TAG);
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

