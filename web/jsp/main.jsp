<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>
<script src="../js/ajax-task-buttons.js"></script>
<html lang="en">
    <head>
        <title>Main page</title>
        <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
        <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="/js/bootstrap.min.js"></script>
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
                <c:import url="/jsp/buttons/createConference.jsp"/>
                <c:import url="/jsp/buttons/deletedConferences.jsp"/>
            </div>
            <c:import url="/jsp/buttons/exit.jsp"/>
            </div>
        <main role="main" class="content">

            <div id="slide">
                <div id="toggle">&#9776;</div>
                <div class="box">
                    <c:import url="/jsp/jspchunks/addConferences.jsp"/>
                </div>
            </div>


            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h5 class="my-0 font-weight-normal text-center">
                            Choose conference
                        </h5>
                        <div>
                            <c:import url="/jsp/jspchunks/taskOutput.jsp"/>
                        </div>
                        <h5 class="my-0 font-weight-normal">
                            or upload .CSV file
                        </h5>
                        <div>
                            <c:import url="/jsp/jspchunks/uploadFile.jsp"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h1 class="text-center">Your events</h1>
                        <c:import url="/jsp/jspchunks/events.jsp"/>
                    </div>
                </div>
            </div>
            </main>
        <footer class="footer fixed-bottom">
            <c:import url="/jsp/jspchunks/developerName.jsp"/>
        </footer>
    </body>
</html>
