modalWindowType = {};

modalWindowType[true] = drawFileModalWindow;
modalWindowType[false] = drawNoFileModalWindow;


function drawModalWindows(taskId, fileName, taskName) {

    var isFileExist = fileName !== NO_FILE;

    removeAllElements(document.getElementById(FILE_MODAL_WINDOW));

    modalWindowType[isFileExist](taskId, fileName, taskName);
}

function getAside(){
    var aside = document.createElement(ASIDE_TAG);

    aside.setAttribute(CLASS_ATTRIBUTE,JS_MODAL_WINDOW);

    var header = document.createElement(HEADER_TAG);

    var h2 = document.createElement(H2_TAG);

    h2.innerHTML = CHOOSE_FILE_ACTION;

    header.appendChild(h2);

    aside.appendChild(header);

    return aside;
}


function drawFileModalWindow(taskId, fileName, taskName) {

    var fileModalWindow = document.getElementById(FILE_MODAL_WINDOW);

    var aside = getAside(CHOOSE_FILE_ACTION);

    var section = document.createElement(SECTION_TAG);

    var h5 = document.createElement(H5_TAG);

    h5.innerHTML = fileName;

    section.appendChild(h5);

    var div = document.createElement(DIV_TAG);

    div.setAttribute(CLASS_ATTRIBUTE,"row text-center");

    var colFirstDiv = document.createElement(DIV_TAG);

    colFirstDiv.setAttribute(CLASS_ATTRIBUTE, "col");

    var form = document.createElement(FORM_ATTRIBUTE); // --------------------------

    form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);

    form.setAttribute(ACTION_ATTRIBUTE, DOWNLOAD_FILE_SERVLET);

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, TASK_NAMES);

    input.setAttribute(VALUE_ATTRIBUTE, taskName);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);

    input.setAttribute(NAME_ATTRIBUTE, FILE_NAMES);

    input.setAttribute(VALUE_ATTRIBUTE, fileName);

    form.appendChild(input);

    var downloadButton = document.createElement(INPUT_TAG);

    downloadButton.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    downloadButton.setAttribute(TYPE_ATTRIBUTE,SUBMIT_ATTRIBUTE);

    downloadButton.setAttribute(VALUE_ATTRIBUTE,"Download");

    var fix = function() {

        form.submit();
        removeAllElements(fileModalWindow);

    };

    downloadButton.addEventListener(CLICK, fix);

    form.appendChild(downloadButton);

    colFirstDiv.appendChild(form); /// -----------------------------------

    div.appendChild(colFirstDiv);

    var colSecondDiv = document.createElement(DIV_TAG);

    colSecondDiv.setAttribute(CLASS_ATTRIBUTE, "col");

    var deleteButton = document.createElement(BUTTON_TAG);

    deleteButton.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");

    deleteButton.innerHTML = DELETE;

    var event = function() {

        sendRequestToDeleteFileServlet(taskId);
        removeAllElements(fileModalWindow);

    };

    deleteButton.addEventListener(CLICK, event);

    colSecondDiv.appendChild(deleteButton);

    div.appendChild(colSecondDiv);

    section.appendChild(div);

    aside.appendChild(section);

    var footer = document.createElement(FOOTER_TAG);

    footer.setAttribute(CLASS_ATTRIBUTE, FOOTER_TAG);

    var closeButton = document.createElement(BUTTON_TAG);

    closeButton.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    closeButton.onclick = function () {

        removeAllElements(fileModalWindow);

    };

    closeButton.innerHTML = CLOSE_HREF;

    footer.appendChild(closeButton);

    aside.appendChild(footer);

    fileModalWindow.appendChild(aside);

}

function drawNoFileModalWindow(taskId, fileName, taskName) {
    var fileModalWindow = document.getElementById(FILE_MODAL_WINDOW);

    var aside = getAside(UPLOAD_FILE);

    var section = document.createElement(SECTION_TAG);

    var form = document.createElement(FORM_ATTRIBUTE);
    form.setAttribute(ECTYPE,MULTIPART);
    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");


    var input = document.createElement(INPUT_TAG);
    input.setAttribute(NAME_ATTRIBUTE, FILE);
    input.setAttribute(TYPE_ATTRIBUTE, FILE);
    input.setAttribute(VALUE_ATTRIBUTE, UPLOAD);
    input.setAttribute(CLASS_ATTRIBUTE, "form-control-file mb-1");
    input.setAttribute(ID_ATTRIBUTE, "uploadFile");

    form.appendChild(input);
    section.appendChild(form);

    var button = document.createElement(BUTTON_TAG);

    button.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger btn-sm");
    button.innerHTML = UPLOAD;

    var event = function () {
        sendRequestToUploadFileServlet(taskId, taskName);
        setTimeout(removeAllElements(fileModalWindow), 1);
    };

    button.addEventListener(CLICK,event);

    section.appendChild(button);
    aside.appendChild(section);
    var footer = document.createElement(FOOTER_TAG);

    footer.setAttribute(CLASS_ATTRIBUTE, FOOTER_TAG);

    var closeButton = document.createElement(BUTTON_TAG);

    closeButton.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    closeButton.onclick = function () {

        removeAllElements(fileModalWindow);

    };

    closeButton.innerHTML = CLOSE_HREF;

    footer.appendChild(closeButton);

    aside.appendChild(footer);

    fileModalWindow.appendChild(aside);


}