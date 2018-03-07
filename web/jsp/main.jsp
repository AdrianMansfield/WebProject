<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>
<html lang="en">
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="../js/general-constants.js"></script>
    <script src="../js/tag-constants.js"></script>
    <script src="../js/attribute-constants.js"></script>
    <script src="../js/remove-all-elements-from-table.js"></script>
    <script src="../js/new-xml-http-request.js"></script>
    <script src="../js/send-query.js"></script>
    <script src="../js/script.js"></script>
    <script src="../js/ajax-conference-print.js"></script>
    <script src="../js/ajax-event-print.js"></script>
    <script src="../js/get-checked-checkbox.js"></script>
    <script src="../js/ajax-move-conference.js"></script>
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
    <div id="leftSlide">
        <div id="leftToggle">AC</div>
        <div class="box">
            <c:import url="/jsp/jspchunks/addConferences.jsp"/>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-6" id="conference">
                <h5 class="my-0 font-weight-normal text-center">
                    Choose task
                </h5>
                <div>
                    <c:import url="/jsp/jspchunks/taskOutput.jsp"/>
                </div>
            </div>
            <div id="fixed"></div>
            <div class="col-md-6" id="events">
                <h1 class="text-center">Task description</h1>
                <c:import url="/jsp/jspchunks/description.jsp"/>
            </div>
            <div id="bucket"></div>
        </div>
    </div>
</main>
<footer class="footer fixed-bottom">
    <c:import url="/jsp/jspchunks/developerName.jsp"/>
</footer>
</body>
</html>
