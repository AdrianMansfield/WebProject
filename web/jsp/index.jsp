<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name = "viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Web project</title>
    <link rel="stylesheet" href="${JspConstants.BOOTSTRAP_STYLE_FILE_PATH}">
    <link rel="stylesheet" href="${JspConstants.MAIN_STYLE_FILE_PATH}">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 navbar-inverse">
    <c:import url="${control}"/>
</div>
<main role="main" class="container">
    <h1>Application start page</h1>
    <p class="lead">Select an action <code>Login</code> or <code>Registration</code></p>
</main>

<footer class="footer fixed-bottom">
    <div class="container">
        <div class="text-muted text-center">Developed by <a href="#">Your name</a>.</div>
    </div>
</footer>
</body>
</html>
