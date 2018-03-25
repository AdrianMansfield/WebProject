<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<thead>
<th>complete</th>

<th>description</th>

<th>file</th>

<c:if test="${taskType eq 'BASKET'}">

    <th>delete</th>

</c:if>


<c:if test="${taskType ne 'BASKET'}">

    <th>to basket</th>

</c:if>

<c:if test="${taskType eq 'SOMEDAY'}">

    <th>date</th>

</c:if>

</thead>