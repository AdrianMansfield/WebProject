<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="table-container">

    <table class="text-center table-bordered table-hover" id="taskTable">
        <thead>
            <th>complete</th>
            <th>description</th>
            <th>file</th>
            <th>delete</th>
            <c:if test="${taskType eq 'SOMEDAY'}">
                <th>date</th>
            </c:if>
        </thead>
        <tbody>
        <c:set var="isBasket" value="${taskType == \"BASKET\"}" scope="request"/>
        <c:set var="isFixed" value="${taskType == \"FIXED\"}" scope="request"/>
        <c:set var="isMain" value="${!isBasket && !isFixed}" scope="request"/>

        <c:forEach var="task" items="${tasks}" varStatus="status">

            <c:set var="taskId" value="${task.id}" scope="request"/>
            <c:set var="taskName" value="${task.name}" scope="request"/>
            <c:set var="description" value="${task.description}" scope="request"/>
            <c:set var="fileName" value="${task.fileName}" scope="request"/>
            <c:set var="fileExists" value="${task.fileName == \"No file\"}" scope="request"/>
            <c:set var="formNumber" value="${status.index}" scope="request"/>
            <c:set var="taskDate" value="${task.date}" scope="request"/>

            <c:import url="/view/jsp/jspchunks/table/firstTableRow.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/descriptionSecondTableRow.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/changeDescriptionRow.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/changeNameRow.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/chooseFileAction.jsp"/>

            <c:import url="/view/jsp/jspchunks/table/uploadFile.jsp"/>

        </c:forEach>
        </tbody>

    </table>

    <c:import url="/view/jsp/jspchunks/table/deleteTaskForm.jsp"/>

</div>
