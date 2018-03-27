<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For name changing -->
<tr class="modalDescription" id="${taskName}ChangeDate">
    <td colspan="5">
        <form action="ChangeTaskServlet" method="post">
            <input type="hidden" name="taskType" value="${taskType}"/>
            <input type="hidden" name="taskId" value="${taskId}">
            <input type="hidden" name="infoType" value="date"/>
            <input type="text" name="taskAttribute" value="${taskDate}" class="form-control"
                   pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" data-valid-example="24.02.2018" placeholder="dd.mm.yyyy" required/>
            <input type="submit" class="btn btn-sm btn-outline-danger">
        </form>
        <a href="#${taskName}" class="btn">close</a>
    </td>
</tr>
