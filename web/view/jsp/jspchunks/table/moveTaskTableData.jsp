<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<td>
    <!-- Form for move task -->
    <form action="MoveTaskServlet" method="post" class="mb-0">
        <input type="hidden" name="taskId" value="${taskId}"/>
        <input name="taskType" type="hidden" value="${taskType}"/>
        <!-- Part of form for add to fixed -->
        <c:if test="${isMain}">
            <input name="locationType" type="hidden" value="fixed"/>
        </c:if>
        <!-- Part of form for restore from basket -->
        <c:if test="${!isMain}">
            <input name="locationType" type="hidden" value="main"/>
        </c:if>
        <input type="submit" value=&#10226 class="btn btn-outline-danger"/>
    </form>
</td>

