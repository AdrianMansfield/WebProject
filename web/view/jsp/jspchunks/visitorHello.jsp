<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h5 class="my-0 mr-md-auto font-weight-normal text-white">
    User: <c:out value="${login}" default="visitor"/>
</h5>
<nav class="my-2 my-md-0 mr-md-3">
    <a class="p-2 text-dark" href="login">Sign in</a>
    <a class="p-2 text-dark" href="registration">Sign up</a>
</nav>
