<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For description of task -->
<tr class="modalDescription" id="${taskName}">
    <td colspan="4">
        <form action="ChangeTaskInfoServlet" method="post">
            <input type="hidden" name="taskType" value="${taskType}"/>
            <input type="hidden" name="taskId" value="${taskId}">
            <input type="hidden" name="infoType" value="description"/>
            <textarea name="description">
                ${description}
            </textarea>
            <input type="submit" class="btn btn-sm btn-outline-danger">
        </form>
        <a href="#" class="btn">close</a>
    </td>
</tr>
