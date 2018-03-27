<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name = "viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Web project</title>
        <link rel="stylesheet" href="../../view/css/bootstrap.css">
        <link rel="stylesheet" href="../../view/css/main.css">
        <link rel="stylesheet" href="../../view/css/bars.css">
    </head>
    <body>
    <c:import url="${control}"/>
        <main role="main" class="container">
            <h1>Application start page</h1>
            <p class="lead">Select an action <code>Login</code> or <code>Registration</code></p>
        </main>
        <footer class="footer fixed-bottom">
            <c:import url="/view/jsp/parts/developerName.jsp"/>
        </footer>
    </body>
</html>
