<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For name changing -->
<tr class="modalDescription" id="${taskName}ChangeName">
    <td colspan="5">
        <form action="ChangeTaskInfoServlet" method="post">
            <input type="hidden" name="taskType" value="${taskType}"/>
            <input type="hidden" name="taskId" value="${taskId}">
            <input type="hidden" name="infoType" value="name"/>
            <textarea name="taskAttribute" class="form-control">
                ${taskName}
            </textarea>
            <input type="submit" class="btn btn-sm btn-outline-danger">
        </form>
        <a href="#${taskName}" class="btn">close</a>
    </td>
</tr>