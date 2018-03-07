<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="PrintEventServlet" method="post" id="tasks" name="taskForm">
    <div class="table-container">
        <table class="text-center table-bordered table-hover" id="taskTable">
            <c:forEach var="task" items="${conferences}" varStatus="status">
                <tr>
                    <td>
                        <%--<input name="completeAttribute" type="submit" value="d" id="${task.id}"/>--%>
                        <input type="submit" name="Choose" class="btn btn-outline-danger"
                               onclick="showEvents(); sendQueryToPrintEventServlet(value); return false;"/>
                    </td>
                    <td>
                        <label class="holder">
                            <c:if test="${fileMap.get(task.name) != null}">
                                ${fileMap.get(task.name)}
                                <div class="block">
                                    <button form="tasks" formaction="DownloadFileServlet"
                                            class="btn btn-outline-danger">
                                        Download
                                    </button>
                                    <button form="tasks" formaction="DeleteFileServlet" class="btn btn-outline-danger">
                                        Delete
                                    </button>
                                </div>
                                <c:set var="file" value="${fileMap.get(task.name)}" scope="page"/>
                            </c:if>
                            <c:if test="${fileMap.get(task.name) == null}">
                                <div class="block">
                                    <button form="tasks" formaction="UploadFileServlet" class="btn btn-outline-danger">
                                        Upload
                                    </button>
                                </div>
                                <c:set var="file" value="No file" scope="page"/>
                            </c:if>
                        </label>
                    </td>
                    <td>
                        <c:if test="${!isBasket && conference.length > 0}">
                            <button form="tasks" formmethod="post" formaction="MoveConferenceServlet"
                                    name="typeLocation"
                                    class="btn btn-outline-danger" value="basket" id="deleteConf">Move to basket
                            </button>
                        </c:if>
                        <c:if test="${isBasket && conference.length > 0}">
                            <button form="tasks" formmethod="post" formaction="DeleteConferenceServlet" name="Delete"
                                    class="btn btn-outline-danger" value="delete" id="deleteConf">Delete
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%--<input type="submit" name="Choose" class="btn btn-outline-danger"--%>
           <%--onclick="showEvents(); sendQueryToPrintEventServlet(value); return false;"/>--%>
</form>