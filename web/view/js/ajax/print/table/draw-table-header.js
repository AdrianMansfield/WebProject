taskDayType = {};

taskDayType['TODAY'] = drawTodayHeaderPart;
taskDayType['TOMORROW'] = drawTomorrowHeaderPart;
taskDayType['SOMEDAY'] = drawSomedayHeaderPart;
taskDayType['FIXED'] = drawFixedHeaderPart;
taskDayType['BASKET'] = drawBasketHeaderPart;

function drawTableHeader(taskType) {
    alert(taskType);
    taskDayType[taskType]();
}

function drawTodayHeaderPart() {

    var thead = document.createElement("thead");
    var th = document.createElement("th");

    th.innerHTML = 'complete';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'task name';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'file';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'delete';

    thead.appendChild(th);

    alert(thead);

    return thead;
}

function drawTomorrowHeaderPart() {
    drawTodayHeaderPart();
}

function drawSomedayHeaderPart() {
    var thead = document.createElement("thead");
    var th = document.createElement("th");

    th.innerHTML = 'complete';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'task name';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'file';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'date';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'delete';

    thead.appendChild(th);
    return thead;
}

function drawFixedHeaderPart() {
    var thead = document.createElement("thead");
    var th = document.createElement("th");

    th.innerHTML = 'restore';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'task name';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'file';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'delete';

    thead.appendChild(th);

    return thead;
}

function drawBasketHeaderPart() {
    var thead = document.createElement("thead");
    var th = document.createElement("th");

    th.innerHTML = 'restore';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'task name';

    thead.appendChild(th);

    th = document.createElement("th");

    th.innerHTML = 'file';

    thead.appendChild(th);

    th = document.createElement("th");

    var checkbox = document.createElement("checkbox");

    checkbox.setAttribute(ID_ATTRIBUTE, "checkAll");

    // checkbox.onclick = toggleAll.bind(this,this);

    th.appendChild(checkbox);

    var label = document.createElement(LABEL_TAG);

    label.setAttribute(FOR_ATTRIBUTE, "checkAll");

    label.innerHTML = "check all";

    th.appendChild(label);

    thead.appendChild(th);

    return thead;
}
