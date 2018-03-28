
function tableHeader(taskType) {

    var thead = document.createElement("thead");
    var th = document.createElement("th");

    if (isBasket(taskType) || isFixed(taskType)){
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

    if (isSomeday(taskType)) {

        th = document.createElement("th");

        th.innerHTML = DATE;

        thead.appendChild(th);
    }

    if (isBasket(taskType)){
        th = document.createElement("th");

        var checkbox = document.createElement(CHECKBOX_TAG);

        checkbox.setAttribute(ID_ATTRIBUTE,"checkAll");

        th.appendChild(checkbox);

        var label = document.createElement(LABEL_TAG);

        label.setAttribute(FOR_ATTRIBUTE,"checkAll");

        label.addEventListener(CLICK,checkAll);

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