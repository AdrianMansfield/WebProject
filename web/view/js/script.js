function isChecked(chekbox) {
    return chekbox.checked;
}

function checkAll() {
    var checkboxNodeList = document.getElementsByName(TASK_IDS);
    var checkboxArray = Array.prototype.slice.call(checkboxNodeList);
    if (checkboxArray.every(isChecked)) {
        for (var i = 0; i < checkboxArray.length; i++) {
            checkboxArray[i].checked = '';
        }
    } else {
        for (var i = 0; i < checkboxArray.length; i++) {
            checkboxArray[i].checked = CHECKED;
        }
    }
}

function isBasket(taskType){
    return taskType === BASKET;
}

function isFixed(taskType) {
    return taskType === FIXED;
}

function isSomeday(taskType) {
    return taskType === SOMEDAY;
}