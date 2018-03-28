<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<thead>
<th>complete</th>

<th>description</th>

<th>file</th>

<c:if test="${isBasket}">

    <th>delete</th>

</c:if>


<c:if test="${!isBasket}">

    <th>to basket</th>

</c:if>

<c:if test="${isSomeday}">

    <th>date</th>

</c:if>

</thead>