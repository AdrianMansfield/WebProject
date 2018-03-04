<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="EventServlet" method="post" id="tasks" name="taskForm">
    <div class="table-container">
        <table class="text-center table-bordered table-hover" id="taskTable">
            <c:forEach var="task" items="${conferences}" varStatus="status">
                <tr>
                    <td>
                        <input name="currentTask" type="radio" class="invisible-circle-label" value="${task.id}"
                               id="${task.id}"/>
                        <label for="${task.id}">${task.name}</label>
                    </td>
                    <td>
                        <c:if test="${fileMap.get(task.name) != null}">
                            <button form="tasks" formaction="DownloadFileServlet" class="btn btn-outline-danger"
                                    formmethod="post" name="file" value="${task.name};${fileMap.get(task.name)}">
                                    ${fileMap.get(task.name)}
                            </button>
                            <c:set var="file" value="${fileMap.get(task.name)}" scope="page"/>
                        </c:if>
                        <c:if test="${fileMap.get(task.name) == null}">
                            <c:set var="file" value="No file" scope="page"/>
                        </c:if>
                    </td>
                    <td>
                        <input type="checkbox" class="form-check-input" name="deleteConferenceCheck" onclick="showDeleteButton('tasks','deleteConf')"
                               value="${task.id}:${task.name};${file}">
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${!isBasket && conference.length > 0}">
                <button form="tasks" formmethod="post" formaction="MoveConferenceServlet"  name="typeLocation"
                        class="btn btn-outline-danger noneDisplay" value= "basket" id="deleteConf">Move to basket</button>
            </c:if>
            <c:if test="${isBasket && conference.length > 0}">
                <button form="tasks" formmethod="post" formaction="DeleteConferenceServlet"  name="Delete"
                        class="btn btn-outline-danger noneDisplay" value= "delete" id="deleteConf">Delete</button>
            </c:if>
        </table>
    </div>
    <input type="submit" name="Choose" class="btn btn-outline-danger" onclick="showEvents(); sendQueryToPrintEventServlet(value); return false;"/>
</form>