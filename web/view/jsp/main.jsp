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
    <script src="../../view/js/general-constants.js"></script>
    <script src="../../view/js/tag-constants.js"></script>
    <script src="../../view/js/tag-attribute-constants.js"></script>
    <script src="../../view/js/remove-all-elements-from-table.js"></script>
    <script src="../../view/js/new-xml-http-request.js"></script>
    <script src="../../view/js/send-request.js"></script>
    <script src="../../view/js/ajax-task-print.js"></script>
    <script src="../../view/js/get-checked-checkbox.js"></script>
    <script src="../../view/js/ajax-move-task.js"></script>
    <script src="../../view/js/script.js"></script>
    <script src="../../view/js/dram-modal-window.js"></script>
    <script src="../../view/js/ajax-add-task.js"></script>
    <script src="../../view/js/ajax-upload-file.js"></script>
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-0 px-md-4 mb-1 navbar-inverse">
    <h5 class="text-white my-0 mr-md-0 mb-1">ToDo App main page</h5>
    <div class="form-inline  mx-auto">
        <c:import url="/view/jsp/buttons/taskButtons.jsp"/>
    </div>
    <c:import url="/view/jsp/buttons/exit.jsp"/>
</div>

<main role="main">
    <div class="custom-row">
        <div class="col-md-3">
            <a href="#leftSlide" class="bigFont text-center">&#9998 Add Task</a>
            <div class="modalWindow" id="leftSlide">
                <c:import url="/view/jsp/jspchunks/addTasks.jsp"/>
            </div>
        </div>
        <div class="col-md-8" id="task">
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
