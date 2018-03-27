<%@ page contentType="text/html;charset=UTF-8"%>

<html>
    <head>
        <title>Error page</title>
    </head>
    <body>
         Request from ${pageContext.errorData.requestURI} isFailed
    <br>
        Servlet name ${pageContext.errorData.servletName}
    <br>
        Status code ${pageContext.errorData.statusCode}
    <br>
        Exception ${pageContext.errorData.statusCode}
    <br>
        Message from exception ${pageContext.exception.message}
    </body>
</html>
