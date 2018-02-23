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
            <c:set var="color" value="black"/>
            <c:if test="${event.name eq 'Opening'}">
                <c:set var="color" value="red"/>
            </c:if>
            <TD><font color="${color}">${event.id}</font></TD>
            <TD><font color="${color}">${event.name}</font></TD>
            <TD><font color="${color}">${event.time}</font></TD>
        </TR>
    </c:forEach>
    </tbody>
</TABLE>
