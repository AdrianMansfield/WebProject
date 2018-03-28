<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tr>
    <!-- Form for move task -->
    <c:import url="/view/jsp/parts/main/table/moveTaskTableData.jsp"/>
    <!-- Link for modal window of description of task -->
    <c:import url="/view/jsp/parts/main/table/taskNameTableData.jsp"/>
    <!-- Link for modal window of file -->
    <c:import url="/view/jsp/parts/main/table/fileNameTableData.jsp"/>
    <!-- Form for add to basket and remove task -->
    <c:import url="/view/jsp/parts/main/table/throwTaskTableData.jsp"/>

    <c:if test="${isSomeday}">
        <c:import url="/view/jsp/parts/main/table/taskDateTableData.jsp"/>
    </c:if>

</tr>
