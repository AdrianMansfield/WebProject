<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-1 px-md-4 mb-2 navbar-inverse">
    <h5 class="text-white my-0 mr-md-0 mb-1">ToDo App main page</h5>
        <form class="form-inline  mb-1" action="/MainServlet" method="post">
            <button class="btn btn-danger p-2 mr-2 ml-4" name = "date" value="today">Today</button>
            <button class="btn btn-danger p-2 mr-2" name = "date" value="tomorrow">Tomorrow</button>
            <button class="btn btn-danger p-2 mr-2" name = "date" value="someday">Someday</button>
        </form>
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
            <form method="post" action="/FileServlet">
                <label class="btn btn-primary">
                    Browse <input type="file" class="custom-file-input"/>
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
