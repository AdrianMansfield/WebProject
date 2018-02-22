<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>

<h5 class="my-0 mr-md-auto font-weight-normal text-white">
    Hello: <c:out value="${login}" default="visitor"/>
</h5>
<nav class="my-2 my-md-0 mr-md-3">
    <a class="p-2 text-dark" href="/jsp/main.jsp">Main page</a>
</nav>
<c:import url="/jsp/buttons/exit.jsp"/>

