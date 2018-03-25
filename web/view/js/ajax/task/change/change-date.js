function setMidnight(date) {

    date.setHours(0);

    date.setMinutes(0);

    date.setSeconds(0);

    date.setMilliseconds(0);

    return date;

}

function changeDate(taskId, date, infoType) {

    var tomorrowDate = new Date();

    tomorrowDate.setDate(tomorrowDate.getDate() + 1);

    tomorrowDate = setMidnight(tomorrowDate);

    var taskDate = new Date(Date.parse(date));

    taskDate = setMidnight(taskDate);

    var section = "";

    if(taskDate > tomorrowDate) {

        section = "SOMEDAY";

    }
    else if(taskDate<tomorrowDate) {

        section = "TODAY";

    }
    else {

        section = "TOMORROW";
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