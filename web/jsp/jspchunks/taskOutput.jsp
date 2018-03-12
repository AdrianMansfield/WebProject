<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="custom-row">
    <div class="col-md-8">
        <div class="table-container">
            <table class="text-center table-bordered table-hover" id="taskTable">
                <c:forEach var="task" items="${conferences}" varStatus="status">
                    <tr>
                        <td>
                            <form action="MoveConferenceServlet" method="post">
                                <input name="typeLocation" type="hidden" value="fixed"/>
                                <input type="hidden" name="deleteConferenceCheck" value="${task.id}"/>
                                <c:if test="${!isBasket}">
                                    <input type="submit" value=&#10004 class="btn btn-outline-danger"/>
                                </c:if>
                                <c:if test="${isBasket}">
                                    <input type="submit" value=&#10226 class="btn btn-outline-danger"/>
                                </c:if>
                            </form>
                        </td>
                        <td>
                            <a href="#${task.name}">${task.name}</a>
                        </td>
                        <td>
                            <c:if test="${fileMap.get(task.name) != null}">
                                <a href="#${task.id}">${fileMap.get(task.name)}</a>
                                <c:set var="taskID" value="${task.id}" scope="page"/>
                            </c:if>
                            <c:if test="${fileMap.get(task.name) == null}">
                                <a href="#${task.id}">No file</a>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!isBasket}">
                                <form action="MoveConferenceServlet" method="post" id="conferenceForm">
                                    <input type="hidden" name="typeLocation" value="basket"/>
                                    <input type="submit" value=&#10006; class="btn btn-outline-danger"/>
                                </form>
                            </c:if>
                            <c:if test="${isBasket}">
                                <form method="post" action="DeleteConferenceServlet" id="basketForm">
                                    <input type="hidden"/>
                                    <input type="checkbox" name="deleteConfCheck">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                    <tr class="modalDescription" id="${task.name}">
                        <td>
                                ${task.description}
                            <a href="#" class="btn">close</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${isBasket && conferences != null}">
                    <input type="submit" value=&#9760 form="basketForm"/>
                </c:if>
            </table>
        </div>
    </div>
    <div class="col-md-4">
        <c:forEach var="task" items="${conferences}" varStatus="status">
            <c:if test="${fileMap.get(task.name) != null}">
                <aside class="modalWindow" id="${task.id}">
                    <header>
                        <h2>Choose file action</h2>
                    </header>
                    <section>
                        <h5>${fileMap.get(task.name)}</h5>
                        <div class="row text-center">
                            <div class="col">
                                <form action="DownloadFileServlet" method="post">
                                    <input type="hidden" name="file" value="${fileMap.get(task.name)}"/>
                                    <input type="submit" value="Download" class="btn btn-outline-danger"/>
                                </form>
                            </div>
                            <div class="col">
                                <form action="DeleteFileServlet" method="post">
                                    <input type="hidden" name="file" value="${fileMap.get(task.name)}"/>
                                    <input type="submit" value="Delete" class="btn btn-outline-danger"/>
                                </form>
                            </div>
                        </div>
                        <c:set var="file" value="${fileMap.get(task.name)}" scope="page"/>
                    </section>
                    <footer class="footer">
                        <a href="#">close</a>
                    </footer>
                </aside>
            </c:if>
            <c:if test="${fileMap.get(task.name) == null}">
                <aside class="modalWindow" id="${task.id}">
                    <header>
                        <h2>Choose file action</h2>
                    </header>
                    <section>
                        <form action="UploadFileServlet" method="post" enctype="multipart/form-data">
                            <input name="file" type="file" value="Upload" class="form-control-file mb-1" style="overflow: hidden"/>
                            <input type="submit" value="Upload" class="btn btn-outline-danger btn-sm">
                        </form>
                        <c:set var="file" value="No file" scope="page"/>
                    </section>
                    <footer class="footer">
                        <a href="#">close</a>
                    </footer>
                </aside>
            </c:if>
        </c:forEach>
    </div>
</div>
