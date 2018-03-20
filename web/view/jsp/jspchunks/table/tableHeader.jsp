<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<thead>
<th>complete</th>

<th>description</th>

<th>file</th>

<th>delete</th>

<c:if test="${taskType eq 'SOMEDAY'}">

    <th>date</th>

</c:if>

</thead>