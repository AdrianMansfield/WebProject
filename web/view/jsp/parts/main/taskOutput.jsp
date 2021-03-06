<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="by.gsu.epamlab.constants.JspConstants" %>
<%@ page import="by.gsu.epamlab.constants.ParameterConstants" %>
<%@ page import="by.gsu.epamlab.constants.Constants" %>

<div class="table-container" id="tableContainer">

    <table class="text-center" id="taskTable">

        <c:set var = "isBasket" value = "${taskType eq JspConstants.BASKET}" scope = "request"/>
        <c:set var = "isFixed" value = "${taskType eq JspConstants.FIXED}" scope = "request"/>
        <c:set var = "isMain"  value =  "${!isBasket && !isFixed}" scope = "request"/>
        <c:set var = "isSomeday" value = "${taskType eq JspConstants.SOMEDAY}" scope = "request"/>

        <c:if test = "${not empty tasks}">
            <c:import url = "/view/jsp/parts/main/table/tableHeader.jsp"/>
        </c:if>


        <tbody>


        <c:forEach var = "task" items = "${tasks}" varStatus = "status">

            <c:set var = "taskId" value = "${task.id}" scope = "request"/>
            <c:set var = "taskName" value = "${task.name}" scope = "request"/>
            <c:set var = "description" value = "${task.description}" scope = "request"/>
            <c:set var = "fileName" value = "${task.fileName}" scope = "request"/>
            <c:set var = "fileExists" value = "${task.fileName eq Constants.NO_FILE}" scope = "request"/>
            <c:set var = "formNumber" value = "${status.index}" scope = "request"/>
            <c:set var = "taskDate" value = "${task.getStringDate()}" scope = "request"/>

            <c:import url = "/view/jsp/parts/main/table/firstTableRow.jsp"/>

            <c:import url = "/view/jsp/parts/main/table/descriptionSecondTableRow.jsp"/>

            <c:import url = "/view/jsp/parts/main/table/changeDescriptionRow.jsp"/>

            <c:import url = "/view/jsp/parts/main/table/changeDateRow.jsp"/>

            <c:import url = "/view/jsp/parts/main/table/chooseFileAction.jsp"/>

            <c:import url = "/view/jsp/parts/main/table/uploadFile.jsp"/>

        </c:forEach>
        </tbody>

    </table>

    <c:import url = "/view/jsp/parts/main/table/deleteTaskForm.jsp"/>

</div>
