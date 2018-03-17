<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- For description of task -->
<tr class="modalDescription" id="${taskName}">
    <td colspan="4">
        <p>${description}</p>
        <a href="#${taskName}Change">Change description</a>
        <a href="#" class="btn">close</a>
    </td>
</tr>
