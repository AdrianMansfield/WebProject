function drawModalWindow(taskId, fileName, taskName) {
    var aside = document.createElement("aside");
    var fileModalWindow = document.getElementById("fileModalWindow");

    aside.setAttribute("class","modalWindow");
    aside.setAttribute("id",taskId);

    var header = document.createElement("header");
    var h2 = document.createElement("h2");

    header.appendChild(h2);
    aside.appendChild(header);

    var section = document.createElement("section");
    var h5 = document.createElement("h5");
    h5.innerHTML = fileName;

    section.appendChild(h5);

    var div = document.createElement("div");

    div.setAttribute("class","row text-center");

    var form = document.createElement("form");

    form.setAttribute("id","fileForm");
    form.setAttribute("action","DownloadFileServlet");
    form.setAttribute("method","post");
    form.setAttribute("class","mb-0");

    var input = document.createElement("input");

    input.setAttribute("type","hidden");
    input.setAttribute("name","taskId");
    input.setAttribute("value",taskId);

    form.appendChild(input);

    input = document.createElement("input");

    input.setAttribute("type","hidden");
    input.setAttribute("name","taskNames");
    input.setAttribute("value",taskName);

    form.appendChild(input);

    input = document.createElement("input");

    input.setAttribute("type","hidden");
    input.setAttribute("name","fileNames");
    input.setAttribute("value",fileName.replace('_',' '));

    form.appendChild(input);
    div.appendChild(form);

    var colFirstDiv = document.createElement("div");

    colFirstDiv.setAttribute("class","col");

    var downloadButton = document.createElement("button");

    downloadButton.setAttribute("form","fileForm");
    downloadButton.setAttribute("formaction","DownloadFileServlet");
    downloadButton.setAttribute("class","btn btn-outline-danger");

    downloadButton.innerHTML = "Download";

    colFirstDiv.appendChild(downloadButton);
    div.appendChild(colFirstDiv);

    var colSecondDiv = document.createElement("div");

    colSecondDiv.setAttribute("class","col");

    var deleteButton = document.createElement("button");

    deleteButton.setAttribute("form","fileForm");
    deleteButton.setAttribute("formaction","DownloadFileServlet");
    deleteButton.setAttribute("class","btn btn-outline-danger");

    deleteButton.innerHTML = "Download";

    colSecondDiv.appendChild(deleteButton);
    div.appendChild(colSecondDiv);

    var footer = document.createElement("footer");

    footer.setAttribute("class","footer");

    var closeButton = document.createElement("button");

    closeButton.setAttribute("class","btn btn-outline-danger");
    closeButton.setAttribute("onclick","removeModalForm();");

    closeButton.innerHTML = "close";

    footer.appendChild(closeButton);
    aside.appendChild(footer);
    fileModalWindow.appendChild(aside);
}