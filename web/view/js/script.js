function toggleAll(source) {
    checkboxes = document.getElementsByTagName(CHECKBOX_TAG);
    for(var checkbox in checkboxes)
        checkbox.checked = source.checked;
}