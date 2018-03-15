<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${isBasket and tasks.size()>0}">
    <tr>
        <td colspan="4">
            <form method = "post" action = "DeleteTaskServlet" id = "delete">
                <input type="hidden" name="taskType" value="${taskType}"/>
                <input type = "submit" value = "Delete" form="delete"/>
            </form>
        </td>
    </tr>
</c:if>
