//
// function addStatement(checkboxStatemnt, checkboxId){
//     var checkboxArray = document.getElementById(checkboxId);
//     var mainCheckbox = document.getElementById(checkboxStatemnt);
//     for (var i = 0; i < (checkboxArray.length); i++) {
//         if(mainCheckbox.checked){
//             checkboxArray[i].checked = "checked";
//         } else{
//             checkboxArray[i].checked = '';
//         }
//     }
// }
//
// function showDeleteButton(checkboxId, buttonId) {
//     var checkboxArray = document.getElementById(checkboxId);
//     var visible = 'none';
//     for (var i = 0; i < (checkboxArray.length); i++) {
//         if (checkboxArray[i].checked){
//             visible = 'inline-block';
//         }
//     }
//     document.getElementById(buttonId).style.display = visible;
// }
//
// function showRestoreButton() {
//     var tasksArray = document.getElementById('tasks');
//     var visible = 'none';
//     for (var i = 0; i < (tasksArray.length); i++) {
//         console.log(tasksArray[i].value);
//         if (tasksArray[i].value !==''){
//             visible = 'inline-block';
//         }
//     }
//     document.getElementById('restoreConf').style.display = visible;
//
// }