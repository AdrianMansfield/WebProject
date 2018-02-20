<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="web/css/main.css"/>
    <title>Sigh in</title>
    <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
</head>
<body class="bg-faded">
<div class="bg-danger text-center">
    <c:if test="${not empty errorMessage}">
        <h5 class="text-white"><c:out value="${errorMessage}"/></h5>
        <hr>
    </c:if>
</div>
<div class="container">
    <div class="card mx-auto">
        <div class="card-header navbar-inverse">
            <h1 class="text-center text-white">Sign in</h1>
        </div>
        <div class="card-body">
            <form action="LoginServlet" method="post">
                <div class="form-group row">
                    <label class="col-md-1 col-form-label offset-md-1">Login:</label>
                    <div class="col-md-8 offset-md-1">
                        <input type="text" class="form-control" name="login" placeholder="Введите логин"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-1 col-form-label offset-md-1">Password:</label>
                    <div class="col-md-8 offset-md-1">
                        <input type="password" class="form-control" name="password" placeholder="Введите пароль"/>
                    </div>
                </div>
                <div class="text-center">
                    <input type="submit" value="Sign in" class="btn btn-danger">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
