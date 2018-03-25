function throwTaskTableData(taskId, isBasket) {

    var td = document.createElement(TD_TAG);

    if (!isBasket) {

        var locationType = BASKET.toLowerCase();

        var button  = document.createElement(BUTTON_TAG);

        button.setAttribute(CLASS_ATTRIBUTE,"btn btn-outline-danger");

        button.onclick = sendQueryToMoveTaskServlet.bind(this, taskId, locationType); //think about it

        button.innerHTML = 'Throw';

        td.appendChild(button);
    }

    else {

        var input = document.createElement(INPUT_TAG);

        input.setAttribute(TYPE_ATTRIBUTE, CHECKBOX_TAG);

        input.setAttribute(NAME_ATTRIBUTE, TASK_IDS);

        input.setAttribute(VALUE_ATTRIBUTE, taskId);

        input.setAttribute(FORM_ATTRIBUTE, DELETE);

        td.appendChild(input);
    }

    return td;
}