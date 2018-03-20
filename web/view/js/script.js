function checkAll()
{
    var table = document.getElementById ('taskTable');
    var checkboxes = table.querySelectorAll ('input[type=checkbox]');
    var val = checkboxes[0].checked;
    for (var i = 0; i < checkboxes.length; i++) checkboxes[i].checked = val;
}