<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ page import="by.gsu.epamlab.constants.JspConstants" %>
<html>
<head>
    <title>Main page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../view/css/bootstrap.css">
    <link rel="stylesheet" href="../../view/css/main.css">
    <link rel="stylesheet" href="../../view/css/fontawesome-all.css">
    <link rel="stylesheet" href="../../view/css/table.css">
    <link rel="stylesheet" href="../../view/css/bars.css">
    <link rel="stylesheet" href="../../view/css/modalWindow.css">
    <!-- Constants and general script -->
    <script src="../../view/js/general-constants.js"></script>
    <script src="../../view/js/tag-constants.js"></script>
    <script src="../../view/js/script.js"></script>
    <script src="../../view/js/tag-attribute-constants.js"></script>
    <script src="../../view/js/get-checked-checkbox.js"></script>
    <!-- Delete -->
    <script src="../../view/js/ajax/delete/remove-all-elements.js"></script>
    <script src="../../view/js/ajax/delete/delete-tasks.js"></script>
    <script src="../../view/js/ajax/delete/remove-task-from-table.js"></script>
    <!-- File -->
    <script src="../../view/js/ajax/file/change-file-name.js"></script>
    <!-- Windows -->
    <script src="../../view/js/windows/draw-file-action-window.js"></script>
    <script src="../../view/js/windows/draw-change-task-info-window.js"></script>
    <!-- Task -->
    <script src="../../view/js/ajax/task/add-task.js"></script>
    <script src="../../view/js/ajax/task/move-task.js"></script>
    <script src="../../view/js/ajax/task/validate-input.js"></script>
    <!-- Change -->
    <script src="../../view/js/ajax/task/change/change-date.js"></script>
    <script src="../../view/js/ajax/task/change/change-task-attributes.js"></script>
    <!-- Print -->
    <script src="../../view/js/ajax/print/table/task-name.js"></script>
    <script src="../../view/js/ajax/print/table/draw-table-header.js"></script>
    <script src="../../view/js/ajax/print/table/move-task.js"></script>
    <script src="../../view/js/ajax/print/table/move-basket-or-remove.js"></script>
    <script src="../../view/js/ajax/print/table/first-table-row.js"></script>
    <script src="../../view/js/ajax/print/table/file-name.js"></script>
    <script src="../../view/js/ajax/print/table/description.js"></script>
    <script src="../../view/js/ajax/print/table/date.js"></script>
    <script src="../../view/js/ajax/print/print-task.js"></script>
    <script src="../../view/js/ajax/print/table/draw-delete-button.js"></script>
    <script src="../../view/js/ajax/print/table/draw-table.js"></script>
    <!-- Requests -->
    <script src="../../view/js/ajax/request/new-xml-http-request.js"></script>
    <script src="../../view/js/ajax/request/send-request.js"></script>
    <script src="../../view/js/ajax/request/request-to-upload-file.js"></script>
    <script src="../../view/js/ajax/request/request-to-add-task.js"></script>
    <script src="../../view/js/ajax/request/request-to-delete-task.js"></script>
    <script src="../../view/js/ajax/request/request-to-change-task.js"></script>
    <script src="../../view/js/ajax/request/request-to-move-task.js"></script>
    <script src="../../view/js/ajax/request/request-to-print-task.js"></script>

</head>
<body>

<nav class="navbar-inverse navbar navbar-light">
    <div class="row">
        <div class="col-md-2">
            <h5 class="text-white mt-1">ToDo App main page</h5>
        </div>
        <div class="col-md-5 offset-3">
            <c:import url="/view/jsp/buttons/taskButtons.jsp"/>
        </div>
        <div class="col-md-2">
            <c:import url="/view/jsp/buttons/exit.jsp"/>
        </div>
    </div>
</nav>
<main role="main">
    <div class="custom-row">
        <div class="col-md-2">
            <a href="#leftSlide" class="bigFont text-center">&#9998 Add Task</a>
        </div>
        <div class="col-md-8" id="task">
            <div id="taskTypeHeader" class="text-center"></div>
            <div class="modalWindow" id="leftSlide">
                <c:import url="/view/jsp/jspchunks/addTasks.jsp"/>
            </div>
            <c:if test="${not empty tasks}">
                <h5 class="my-0 font-weight-normal text-center" id="tasksType">
                        ${taskType}
                </h5>
            </c:if>
            <c:import url="/view/jsp/jspchunks/taskOutput.jsp"/>
        </div>
    </div>
    <div id="fileModalWindow"></div>
</main>
<footer class="footer fixed-bottom">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center">
            <h5>
                <c:out value="${errorMessage}"/>
            </h5>
        </div>
    </c:if>

    <c:import url="/view/jsp/jspchunks/developerName.jsp"/>
</footer>
</body>
</html>
