<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<td>
    <c:if test="${!isBasket}">
        <form action="MoveTaskServlet" method="post" id="taskForm" class="mb-0">
            <input type="hidden" name="taskId" value="${taskId}"/>
            <input type="hidden" name="locationType" value="basket"/>
            <input name="taskType" type="hidden" value="${taskType}"/>
            <input type="submit" value=&#10006; class="btn btn-outline-danger"/>
        </form>
    </c:if>
    <!-- Form for basket -->
    <c:if test="${isBasket}">
        <input type="checkbox" name="taskIds" value="${taskId}" form = "delete"/>
    </c:if>
</td>
