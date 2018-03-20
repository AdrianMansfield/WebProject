<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For description of task -->
<tr class="modalDescription" id="${taskName}">
    <td colspan="5">
        <p>${description}</p>
        <a href="#${taskName}ChangeDescription">Change description</a>
        <a href="#${taskName}ChangeName">Change name</a>
        <a href="#${taskName}ChangeDate">Change date</a>
        <a href="#" class="btn">close</a>
    </td>
</tr>
