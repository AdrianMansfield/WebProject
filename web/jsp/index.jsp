<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name = "viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Web project</title>
        <<link rel="stylesheet" href="../css/bootstrap.css">
        <link rel="stylesheet" href="../css/main.css">
    </head>
    <body>
        <div class="d-flex flex-column flex-md-row align-items-center p-0 px-md-4 mb-1 navbar-inverse">
            <c:import url="${control}"/>
        </div>
        <main role="main" class="container">
            <h1>Application start page</h1>
            <p class="lead">Select an action <code>Login</code> or <code>Registration</code></p>
        </main>
        <footer class="footer fixed-bottom">
            <c:import url="/jsp/jspchunks/developerName.jsp"/>
        </footer>
    </body>
</html>
