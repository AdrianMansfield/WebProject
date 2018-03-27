<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>


<nav class="navbar-inverse navbar navbar-light">
    <div class="row">
        <div class="col">
            <h5 class="mt-1 text-white float-left">
                Hello: <c:out value="${login}" default="visitor"/>
            </h5>
        </div>
        <div class="col text-center">
            <a class="p-2 text-dark" href="../main">Main page</a>
        </div>
        <div class="col">
            <c:import url="/view/jsp/buttons/exit.jsp"/>
        </div>
    </div>
</nav>

