<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="custom-row">
    <div class="col-md-8">
        <div class="table-container">
            <table class="text-center table-bordered table-hover" id="taskTable">
                <c:forEach var="task" items="${tasks}" varStatus="status">
                    <tr>
                        <td>
                            <!-- Form for move task -->
                            <form action="MoveTaskServlet" method="post">
                                <input type="hidden" name="taskId" value="${task.id}"/>
                                <!-- Part of form for add to fixed -->
                                <c:if test="${taskType != \"BASKET\"}">
                                    <input name="taskType" type="hidden" value="${taskType}"/>
                                    <input name="locationType" type="hidden" value="fixed"/>
                                    <input type="submit" value=&#10004 class="btn btn-outline-danger"/>
                                </c:if>
                                <!-- Part of form for restore from basket -->
                                <c:if test="${taskType == \"BASKET\"}">
                                    <input name="taskType" type="hidden" value="${taskType}"/>
                                    <input name="locationType" type="hidden" value="main"/>
                                    <input type="submit" value=&#10226 class="btn btn-outline-danger"/>
                                </c:if>
                            </form>
                        </td>
                        <!-- Link for modal window of description of task -->
                        <td>
                            <a href="#${task.name}">${task.name}</a>
                        </td>
                        <!-- Link for modal window of file -->
                        <td>
                            <a href="#${task.id}">${task.fileName}</a>
                        </td>

                        <!-- Form for add to basket -->
                        <td>
                            <c:if test="${taskType != \"BASKET\"}">
                                <form action="MoveTaskServlet" method="post" id="taskForm">
                                    <input type="hidden" name="taskId" value="${task.id}"/>
                                    <input type="hidden" name="locationType" value="basket"/>
                                    <input name="taskType" type="hidden" value="${taskType}"/>
                                    <input type="submit" value=&#10006; class="btn btn-outline-danger"/>
                                </form>
                            </c:if>

                            <!-- Form for basket -->
                            <c:if test="${taskType == \"BASKET\"}">
                                <form method="post" action="DeleteTaskServlet"">
                                    <input type = "hidden" name = "taskIds" value = "${task.id}"/>
                                    <input type = "hidden" name = "taskNames" value = "${task.name}"/>
                                    <input type = "hidden" name = "fileNames" value = "${task.fileName}"/>
                                    <input type = "hidden" name = "taskType" value = "${taskType}"/>
                                    <input type="submit" value="&#9760"/>
                                </form>
                            </c:if>
                        </td>
                    </tr>

                    <!-- For description of task -->
                    <tr class="modalDescription" id="${task.name}">
                        <td colspan = "4">
                                ${task.description}
                            <a href="#" class="btn">close</a>
                        </td>
                    </tr>


                    <!-- Form for choose file action -->
                    <c:if test="${task.fileName != \"No file\"}">
                        <aside class="modalWindow left-side" id="${task.id}">
                            <header>
                                <h2>Choose file action</h2>
                            </header>
                            <section>
                                <h5>${task.fileName}</h5>
                                <div class="row text-center">
                                    <form id = "${status.index}" action="DownloadFileServlet" method="post">
                                        <input type = "hidden" name = "taskType" value = "${taskType}"/>
                                        <input type = "hidden" name = "taskNames" value = "${task.name}"/>
                                        <input type = "hidden" name = "fileNames" value = "${task.fileName}"/>
                                    </form>
                                    <div class="col">
                                        <button form = "${status.index}" formaction = "DownloadFileServlet" class="btn btn-outline-danger">Download</button>
                                    </div>
                                    <div class="col">
                                        <button form = "${status.index}" formaction = "DeleteFileServlet" class="btn btn-outline-danger">Delete</button>
                                    </div>
                                </div>
                            </section>
                            <footer class="footer">
                                <a href="#">close</a>
                            </footer>
                        </aside>
                    </c:if>

                    <!-- Form for upload file -->
                    <c:if test="${task.fileName  == \"No file\"}">
                        <aside class="modalWindow left-side" id="${task.id}">
                            <header>
                                <h2>Upload file</h2>
                            </header>
                            <section>
                                <form action="UploadFileServlet" method="post" enctype="multipart/form-data">
                                    <input type = "hidden" name = "taskType" value = "${taskType}"/>
                                    <input type = "hidden" name = "taskName" value = "${task.name}"/>
                                    <input name="file" type="file" value="Upload" class="form-control-file mb-1" style="overflow: hidden"/>
                                    <input type="submit" value="Upload" class="btn btn-outline-danger btn-sm">
                                </form>
                            </section>
                            <footer class="footer">
                                <a href="#">close</a>
                            </footer>
                        </aside>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
