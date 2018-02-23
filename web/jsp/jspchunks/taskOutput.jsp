<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<form action="EventServlet" method = "post">
    <c:forEach var="task" items="${tasks}" varStatus="status">
        <div><input name="currentTask" type="radio" value="${task.id}"/> ${task.name}</div>
    </c:forEach>
    <input type = "submit" name = "Choose"/>
</form>