<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<html lang="en">
    <head>
        <title>Sigh in</title>
        <link rel="stylesheet" href="../../view/css/bootstrap.css">
        <link rel="stylesheet" href="../../view/css/main.css">
        <link rel="stylesheet" href="../../view/css/authorization.css">
        <link rel="stylesheet" href="../../view/css/bars.css">
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
                        <c:import url="/view/jsp/jspchunks/authorizationInputs.jsp"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
