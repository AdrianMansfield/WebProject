

function changeDate(taskId, date, infoType) {

    var tomorrowDate = new Date();

    tomorrowDate.setDate(tomorrowDate.getDate() + 1);

    var taskDate = new Date(Date.parse(date));

    var section;

    if(taskDate === tomorrowDate) {
        section = "TOMORROW";
    }

    else if(taskDate<tomorrowDate) {
        section = "TODAY";
    }
    else {
        section = "SOMEDAY";
    }

    date = russianDateFormat(taskDate);

    var currentTaskType = document.getElementById("sectionName").getAttribute("name");

    if(currentTaskType === section) {
        sectionType[section](taskId, date, infoType);
    }
    else {
        removeTaskFromTable(taskId);
    }
}


function russianDateFormat(date) {

    var day = date.getDate();

    var month = date.getMonth() + 1;

    var year = date.getFullYear();

    if(day<10) {
        day = "0" + day;
    }

    if(month<10) {
        month = "0" + month;
    }

    return day + "." + month + "." + year;
}