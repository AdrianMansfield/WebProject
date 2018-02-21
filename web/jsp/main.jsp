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
<div class="d-flex flex-column flex-md-row align-items-center p-1 px-md-4 mb-2 navbar-inverse">
    <h5 class="text-white my-0 mr-md-0 mb-1">ToDo App main page</h5>

    <c:import url="/jsp/exit.jsp"/>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 d-none d-md-block bg-faded sidebar">
            <div class="sidebar-sticky">
                <div class="nav flex-column">
                    <form class="" action="/MainServlet" method="post">
                        <li class="nav-item">
                            <button class="btn btn-danger" name="date" value="today">Today</button>
                        </li>
                        <li class="nav-item">
                            <button class="btn btn-danger" name="date" value="tomorrow">Tomorrow</button>
                        </li>
                        <li class="nav-item">
                            <button class="btn btn-danger" name="date" value="someday">Someday</button>
                        </li>
                    </form>
                    <form class="" action="/CreateConferenceServlet" method="post">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <button type="submit" class="btn btn-danger" name="date" value="addNewConference">
                                    Create new conference
                                </button>
                            </li>
                        </ul>
                    </form>
                    <form class="" action="/DeletedConferencesServlet" method="post">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <button type="submit" class="btn btn-danger" name="date"
                                        value="showDeletedConferences">
                                    Deleted conferences
                                </button>
                            </li>
                        </ul>
                    </form>
                    </ul>
                </div>
            </div>
        </div>
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
