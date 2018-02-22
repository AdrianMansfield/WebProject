<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="EventServlet" method="post">
    <div class="input-group">
        <select name="currentTask" class="custom-select w-75">
            <c:forEach var="task" items="${tasks}" varStatus="status">
                <option value="${task.id}"
                        <c:if test="${status.first}">
                            selected
                        </c:if>
                >${task.name}</option>
            </c:forEach>
        </select>
        <div class="input-group-append">
            <input type="submit" value="OK" class="btn btn-outline-danger"/>
        </div>
    </div>
</form>
