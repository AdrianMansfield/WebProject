<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        </TR>
    </c:forEach>
    </tbody>
</TABLE>
