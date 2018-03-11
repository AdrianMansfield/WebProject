<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>
<html>
<head>
    <title>Main page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="../css/fontawesome-all.css">
    <link rel="stylesheet" href="../css/modalWindow.css">
    <script src="../js/general-constants.js"></script>
    <script src="../js/tag-constants.js"></script>
    <script src="../js/attribute-constants.js"></script>
    <script src="../js/remove-all-elements-from-table.js"></script>
    <script src="../js/new-xml-http-request.js"></script>
    <script src="../js/send-request.js"></script>
    <script src="../js/script.js"></script>
    <script src="../js/ajax-task-print.js"></script>
    <script src="../js/get-checked-checkbox.js"></script>
    <script src="../js/ajax-move-task.js"></script>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <h5 class="text-white">
        <c:out value="${errorMessage}"/>
    </h5>
    <hr>
</c:if>
<div class="d-flex flex-column flex-md-row align-items-center p-0 px-md-4 mb-1 navbar-inverse">
    <h5 class="text-white my-0 mr-md-0 mb-1">ToDo App main page</h5>
    <div class="form-inline  mx-auto">
        <c:import url="/jsp/buttons/taskButtons.jsp"/>
    </div>
    <c:import url="/jsp/buttons/exit.jsp"/>
</div>

<main role="main" class="content">
    <div class="row">
        <div class="col-md-3">
            <a href="#leftSlide" class="bigFont text-center">&#9998 Add Task</a>
            <div class="modalWindow" id="leftSlide">
                <c:import url="/jsp/jspchunks/addTasks.jsp"/>
            </div>
        </div>
        <div class="col-md-9" id="task">
            <h5 class="my-0 font-weight-normal text-center">
                Choose Task
            </h5>
            <div>
                <c:import url="/jsp/jspchunks/taskOutput.jsp"/>
            </div>
        </div>
    </div>
</main>
<footer class="footer fixed-bottom">
    <c:import url="/jsp/jspchunks/developerName.jsp"/>
</footer>
</body>
</html>
