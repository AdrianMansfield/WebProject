<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="table-container">

    <table class="text-center table-bordered table-hover" id="taskTable">

        <c:set var = "isBasket" value = "${taskType == \"BASKET\"}" scope = "request"/>
        <c:set var = "isFixed" value = "${taskType == \"FIXED\"}" scope = "request"/>
        <c:set var = "isMain" value = "${!isBasket && !isFixed}" scope = "request"/>

        <c:forEach var = "task" items = "${tasks}" varStatus = "status">

            <c:set var = "taskId" value = "${task.id}" scope = "request"/>
            <c:set var = "taskName" value = "${task.name}" scope = "request"/>
            <c:set var = "description" value = "${task.description}" scope = "request"/>
            <c:set var = "fileName" value = "${task.fileName}" scope = "request"/>
            <c:set var = "fileExists" value = "${task.fileName == \"No file\"}" scope = "request"/>
            <c:set var = "formNumber" value = "${status.index}" scope = "request"/>

            <tr>
                <!-- Form for move task -->
                <c:import url="/view/jsp/jspchunks/table/moveTaskTableData.jsp"/>
                <!-- Link for modal window of description of task -->
                <c:import url="/view/jsp/jspchunks/table/taskNameTableData.jsp"/>
                <!-- Link for modal window of file -->
                <c:import url="/view/jsp/jspchunks/table/fileNameTableData.jsp"/>
                <!-- Form for add to basket -->
                <!-- Form for add to basket -->
                <td>
                    <c:if test="${!isBasket}">
                        <form action="MoveTaskServlet" method="post" id="taskForm" class="mb-0">
                            <input type="hidden" name="taskId" value="${taskId}"/>
                            <input type="hidden" name="locationType" value="basket"/>
                            <input name="taskType" type="hidden" value="${taskType}"/>
                            <input type="submit" value=&#10006; class="btn btn-outline-danger"/>
                        </form>
                    </c:if>

                    <!-- Form for basket -->
                    <c:if test="${isBasket}">
                        <input type="checkbox" name="taskIds" value="${task.id}" form = "delete"/>
                    </c:if>
                </td>
            </tr>

            <c:import url="/view/jsp/jspchunks/table/descriptionSecondTableRow.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/chooseFileAction.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/uploadFile.jsp"/>

        </c:forEach>

        <c:if test="${isBasket and tasks.size()>0}">
            <tr>
                <td colspan="4">
                    <form method = "post" action = "DeleteTaskServlet" id = "delete">
                        <input type="hidden" name="taskType" value="${taskType}"/>
                        <input type = "submit" value = "Delete" form="delete"/>
                    </form>
                </td>
            </tr>
        </c:if>

    </table>



</div>
