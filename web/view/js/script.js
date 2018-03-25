function toggle(source) {
    var checkboxes = document.getElementsByName(TASK_IDS);
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] !== source)
            checkboxes[i].checked = source.checked;
    }
}