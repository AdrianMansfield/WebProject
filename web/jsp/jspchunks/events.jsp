<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="" method="post" id="events">
    <TABLE align="center">
        <thead>
        <th>Id</th>
        <th>Event</th>
        <th>Time</th>
        </thead>
        <tbody>
        <c:forEach var="event" items="${events}">
            <TR>
                <TD>${event.id}</TD>
                <TD>${event.name}</TD>
                <TD>${event.time}</TD>
                <td>#file-name</td>
                <td>
                    <input type="checkbox" class="form-check-input" id="exampleCheck1"> label
                </td>
            </TR>
        </c:forEach>
        </tbody>
    </TABLE>
    <input type="submit" name="delete" class="btn btn-outline-danger" value="Delete"/>
</form>

