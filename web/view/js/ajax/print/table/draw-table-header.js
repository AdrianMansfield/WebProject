
function tableHeader(taskType) {

    var thead = document.createElement("thead");
    var th = document.createElement("th");

    if (taskType === 'BASKET' || taskType === 'FIXED'){
        th.innerHTML = 'restore';
    } else{
        th.innerHTML = 'complete';
    }

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'task name';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'file';

    thead.appendChild(th);

    if (taskType === "SOMEDAY") {

        th = document.createElement("th");

        th.innerHTML = 'date';

        thead.appendChild(th);
    }

    if (taskType === "BASKET"){
        th = document.createElement("th");

        var checkbox = document.createElement("checkbox");

        checkbox.setAttribute(ID_ATTRIBUTE,"checkAll");

        // checkbox.setAttribute(ONCLICK_ATTRIBUTE,"toggle(this)");

        th.appendChild(checkbox);

        var label = document.createElement(LABEL_TAG);

        label.setAttribute(FOR_ATTRIBUTE,"checkAll");

        label.innerHTML = "check all";

        th.appendChild(label);

        thead.appendChild(th);
    } else {
        th = document.createElement("th");

        th.innerHTML = 'to basket';

        thead.appendChild(th);
    }

    return thead;
}