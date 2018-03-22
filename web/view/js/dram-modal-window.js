modalWindowType = {};

modalWindowType[true] = drawFileModalWindow;
modalWindowType[false] = drawNoFileModalWindow;


function drawModalWindows(/*taskId,*/ fileName, taskName) {

    var isFileExist = fileName !== NO_FILE;

    document.getElementById("fileModalWindow").innerHTML = "";

    modalWindowType[isFileExist](/*taskId,*/ fileName, taskName);
}


function drawFileModalWindow(/*taskId,*/ fileName, taskName) {

    var aside = document.createElement(ASIDE_TAG);
    var fileModalWindow = document.getElementById(FILE_MODAL_WINDOW);

    aside.setAttribute(CLASS_ATTRIBUTE,JS_MODAL_WINDOW);
    //aside.setAttribute(ID_ATTRIBUTE,taskId);

    var header = document.createElement(HEADER_TAG);
    var h2 = document.createElement(H2_TAG);

    h2.innerHTML = CHOOSE_FILE_ACTION;

    header.appendChild(h2);
    aside.appendChild(header);

    var section = document.createElement(SECTION_TAG);
    var h5 = document.createElement(H5_TAG);
    h5.innerHTML = fileName;

    section.appendChild(h5);

    var div = document.createElement(DIV_TAG);

    div.setAttribute(CLASS_ATTRIBUTE,"row text-center");

    var form = document.createElement(FORM_ATTRIBUTE);

    form.setAttribute(ID_ATTRIBUTE, FILE_FORM);
    form.setAttribute(ACTION_ATTRIBUTE, DOWNLOAD_FILE_SERVLET);
    form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);
    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);
    input.setAttribute(NAME_ATTRIBUTE, "taskNames");
    input.setAttribute(VALUE_ATTRIBUTE, taskName);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);
    input.setAttribute(NAME_ATTRIBUTE, FILE_NAMES);
    input.setAttribute(VALUE_ATTRIBUTE, fileName.replace('_',' '));

    form.appendChild(input);
    div.appendChild(form);

    var colFirstDiv = document.createElement(DIV_TAG);

    colFirstDiv.setAttribute(CLASS_ATTRIBUTE, "col");

    var downloadButton = document.createElement(BUTTON_TAG);

    downloadButton.setAttribute(FORM_ATTRIBUTE, FILE_FORM);
    downloadButton.setAttribute(FORMACTION_ATTRIBUTE, DOWNLOAD_FILE_SERVLET);
    downloadButton.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");

    downloadButton.innerHTML = "Download";

    colFirstDiv.appendChild(downloadButton);
    div.appendChild(colFirstDiv);

    var colSecondDiv = document.createElement("div");

    colSecondDiv.setAttribute(CLASS_ATTRIBUTE, "col");

    var deleteButton = document.createElement(BUTTON_TAG);

    deleteButton.setAttribute(FORM_ATTRIBUTE, FILE_FORM);
    deleteButton.setAttribute(FORMACTION_ATTRIBUTE, DELETE_FILE_SERVLET);
    deleteButton.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");

    deleteButton.innerHTML = DELETE;

    colSecondDiv.appendChild(deleteButton);
    div.appendChild(colSecondDiv);
    section.appendChild(div);
    aside.appendChild(section);

    var footer = document.createElement(FOOTER_TAG);

    footer.setAttribute(CLASS_ATTRIBUTE, FOOTER_TAG);

    var closeButton = document.createElement(BUTTON_TAG);

    closeButton.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");
    closeButton.setAttribute(ONCLICK_ATTRIBUTE,"closeModalWindow();");

    closeButton.innerHTML = CLOSE_HREF;

    footer.appendChild(closeButton);
    aside.appendChild(footer);
    fileModalWindow.appendChild(aside);
}

function drawNoFileModalWindow(/*taskId,*/ fileName, taskName) {

    var aside = document.createElement(ASIDE_TAG);
    var fileModalWindow = document.getElementById(FILE_MODAL_WINDOW);

    aside.setAttribute(CLASS_ATTRIBUTE, JS_MODAL_WINDOW);

    var header = document.createElement(HEADER_TAG);
    var h2 = document.createElement(H2_TAG);

    h2.innerHTML = UPLOAD_FILE;

    header.appendChild(h2);
    aside.appendChild(header);

    var section = document.createElement(SECTION_TAG);
    var form = document.createElement(FORM_ATTRIBUTE);

    form.setAttribute(ACTION_ATTRIBUTE, UPLOAD_FILE_SERVLET);
    form.setAttribute(METHOD_ATTRIBUTE, POST_METHOD);
    form.setAttribute(ECTYPE,MULTIPART);
    form.setAttribute(CLASS_ATTRIBUTE, "mb-0");

    var input = document.createElement(INPUT_TAG);

    input.setAttribute(TYPE_ATTRIBUTE, HIDDEN_ATTRIBUTE);
    input.setAttribute(NAME_ATTRIBUTE, TASK_NAME);
    input.setAttribute(VALUE_ATTRIBUTE, taskName);

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);
    input.setAttribute(NAME_ATTRIBUTE, FILE);
    input.setAttribute(TYPE_ATTRIBUTE, FILE);
    input.setAttribute(VALUE_ATTRIBUTE, UPLOAD);
    input.setAttribute(CLASS_ATTRIBUTE, "form-control-file mb-1");
    input.setAttribute(ID_ATTRIBUTE, "uploadFile"); ///

    form.appendChild(input);

    input = document.createElement(INPUT_TAG);
    input.setAttribute(TYPE_ATTRIBUTE, SUBMIT_ATTRIBUTE);
    input.setAttribute(VALUE_ATTRIBUTE, UPLOAD);
    input.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger btn-sm");
    input.onclick = sendQueryToUploadFileServlet.bind(this, taskName);

    form.appendChild(input);

    section.appendChild(form);
    aside.appendChild(section);

    var footer = document.createElement(FOOTER_TAG);

    footer.setAttribute(CLASS_ATTRIBUTE, FOOTER_TAG);

    var closeButton = document.createElement(BUTTON_TAG);

    closeButton.setAttribute(CLASS_ATTRIBUTE, "btn btn-outline-danger");
    closeButton.setAttribute(ONCLICK_ATTRIBUTE, "closeModalWindow();"); //CORRECT

    closeButton.innerHTML = CLOSE_HREF;

    footer.appendChild(closeButton);
    aside.appendChild(footer);
    fileModalWindow.appendChild(aside);

}

function closeModalWindow() {
    var fileModalWindow = document.getElementById(FILE_MODAL_WINDOW);
    fileModalWindow.innerHTML = "";
    
}