<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="EventServlet" method="post">
    <div class="table-container">
        <table class="text-center table-bordered table-hover" style="table-layout: fixed; width: 540px">
            <c:forEach var="task" items="${tasks}" varStatus="status">
                <tr>
                    <td width="80%">
                        <input name="currentTask" type="radio" class="invisible-circle-label" value="${task.id}" id="${task.id}"/>
                        <label for="${task.id}">${task.name}</label>
                    </td>
                    <td width="20%">
                        <label>file name</label>
                    </td>
                </tr>
            </c:forEach>
        </table>
</div>
    <input type="submit" name="Choose" class="btn btn-outline-danger"/>
</form>
