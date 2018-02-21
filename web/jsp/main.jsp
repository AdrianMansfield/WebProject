<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<html lang="en">
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-0 px-md-4 mb-1 navbar-inverse">
    <h5 class="text-white my-0 mr-md-0 mb-1">ToDo App main page</h5>
    <div class="form-inline  mx-auto ">
        <form action="/MainServlet" method="post" class="my-0 mr-md-0 mb-1">
            <button class="btn btn-danger " name="date" value="today">Today</button>
            <button class="btn btn-danger " name="date" value="tomorrow">Tomorrow</button>
            <button class="btn btn-danger " name="date" value="someday">Someday</button>
        </form>
        <form action="/CreateConferenceServlet" method="post" class="my-0 mr-md-0 mb-1">
            <button type="submit" class="btn btn-danger" name="date" value="addNewConference">
                Create new conference
            </button>
        </form>
        <form action="/DeletedConferencesServlet" method="post" class="my-0 mr-md-0 mb-1">
            <button type="submit" class="btn btn-danger" name="date"
                    value="showDeletedConferences">
                Deleted conferences
            </button>
        </form>
    </div>
    <c:import url="/jsp/exit.jsp"/>
</div>
    <main role="main" class="content container">
        <div class="row">
            <div class="col-md-6">
                <h5 class="my-0 font-weight-normal">
                    Choose conference
                </h5>
                <form action="/EventServlet" method="post">
                    <div class="input-group">
                        <select name="currentTask" class="custom-select w-75">
                            <c:forEach var="task" items="${tasks}" varStatus="status">
                                <option value="${task.id}"
                                        <c:if test="${status.first}">
                                            selected
                                        </c:if>
                                >${task.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append">
                            <input type="submit" value="OK" class="btn btn-outline-danger"/>
                        </div>
                    </div>
                </form>
                <h5 class="my-0 font-weight-normal">
                    or upload .CSV file
                </h5>
                <form method="post" action="/FileServlet" enctype="multipart/form-data">
                    <label class="btn btn-outline-secondary custom-file w-75">
                        Choose file<input type="file" class="custom-file-input" name="file"/>
                    </label>
                    <input type="submit" value="Upload" class="btn btn-outline-danger"/>
                </form>
            </div>
            <div class="col-md-6">
                <h1 class="text-center">Your events</h1>
                <c:import url="/jsp/events.jsp"/>
            </div>
        </div>
    </main>
    <footer class="footer fixed-bottom">
        <div class="container">
            <div class="text-muted text-center">Developed by <a href="#">Your name</a>.</div>
        </div>
    </footer>
</body>
</html>
