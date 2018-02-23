<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<form action="EventServlet" method = "post">
    <div class="table-container table table-hover">
        <table class="task-table">
            <c:forEach var="task" items="${tasks}" varStatus="status">
                <tr>
                    <td>
                        <input name="currentTask" type="radio" class="invisible-circle-label" value="${task.id}" id="currentTask"/>
                        <label for="currentTask">${task.name}</label>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <input type = "submit" name = "Choose" class="btn btn-outline-danger"/>
</form>