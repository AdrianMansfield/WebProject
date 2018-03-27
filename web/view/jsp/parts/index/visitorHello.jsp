<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<nav class="navbar-inverse navbar navbar-light">
    <div class="row">
        <div class="col-md-2">
            <h5 class="mt-1 float-left text-white">
                User: <c:out value="${login}" default="visitor"/>
            </h5>
        </div>
        <div class="col-md-2 offset-8">
            <a class="p-2 text-dark" href="login">Sign in</a>
            <a class="p-2 text-dark" href="registration">Sign up</a>
        </div>
    </div>
</nav>
