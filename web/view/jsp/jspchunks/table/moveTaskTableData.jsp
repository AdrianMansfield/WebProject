<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<td>
    <!-- Form for move task -->
    <form action="MoveTaskServlet" method="post" class="mb-0">
        <input type="hidden" name="taskId" value="${taskId}"/>
        <!-- Part of form for add to fixed -->
        <c:if test="${isMain}">
            <input name="taskType" type="hidden" value="${taskType}"/>
            <input name="locationType" type="hidden" value="fixed"/>
            <input type="submit" value=&#10004 class="btn btn-outline-danger"/>
        </c:if>
        <!-- Part of form for restore from basket -->
        <c:if test="${!isMain}">
            <input name="taskType" type="hidden" value="${taskType}"/>
            <input name="locationType" type="hidden" value="main"/>
            <input type="submit" value=&#10226 class="btn btn-outline-danger"/>
        </c:if>
    </form>
</td>

