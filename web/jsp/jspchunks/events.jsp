<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="" method="post" id="events">
    <TABLE align="center">
        <thead>
        <th>Id</th>
        <th>Event</th>
        <th>Time</th>
        </thead>
        <tbody  name="eventTable">
        <c:forEach var="event" items="${events}">
            <TR>
                <TD>${event.id}</TD>
                <TD>${event.name}</TD>
                <TD>${event.time}</TD>
                <td>
                    <input type="checkbox" class="form-check-input" name="deleteEventCheck" onclick="showDeleteButton('events','deleteEvent')">
                </td>
            </TR>
        </c:forEach>
        </tbody>
    </TABLE>
    <input type="button" name="checkAll" class="btn btn-outline-danger" value="check all" id="check" onclick="addStatement('checked','events'); showDeleteButton('events','deleteEvent')"/>
    <input type="button" name="uncheckAll" class="btn btn-outline-danger" value="uncheck all" id="uncheck" onclick="addStatement('','events');showDeleteButton('events','deleteEvent')"/>
    <input type="submit" name="delete" class="btn btn-outline-danger" value="Delete" id="deleteEvent"/>
</form>

