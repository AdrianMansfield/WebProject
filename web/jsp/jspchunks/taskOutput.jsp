<!-- Ты наверное задаешься вопросом, почему я предпочел формы кнопкам. Все просто, с кнопками особо не разгонишься,
а формы проще и легче совершенствовать и что-то в них добавлять по необходимости. в добавок ко всему, я хочу потом все распихать по разным
файлам. Главной формы больше нет, но она и не нужна. Проще связывать кнопки с конкретными формами, под конкретную задачу.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <!-- с работающим js будет еще лучше... там есть иконки .. но они через js  -->
                    </form>
                </td>
                <td>
                    <a href="#modalWindow">${task.name}</a>
                    <aside class="modalWindow" id="modalWindow">
                        <header>
                            <h2>Task description</h2>
                        </header>
                        <section>
                            ${task.name}
                        </section>
                        <footer class="footer">
                            <a href="#" class="btn">close</a>
                        </footer>
                    </aside>
                    <%--<button name="taskId" value="${task.id}">--%>
                            <%--${task.name} <!-- Еще одна форма. Или просто кнопка, если варик с css прокатит -->--%>
                    <%--</button>--%>
                </td>
                <td>

                    <c:if test="${fileMap.get(task.name) != null}">
                        <a href="#modalWindow">${fileMap.get(task.name)}</a>
                        <aside class="modalWindow" id="modalWindow">
                            <header>
                                <h2>Choose file action</h2>
                            </header>
                            <section>
                                <form action="DownloadFileServlet" method="post">
                                    <input type="hidden" name="file" value="${fileMap.get(task.name)}"/>
                                    <input type="submit" value="Download" class="btn btn-outline-danger"/>
                                </form>
                                <form action="DeleteFileServlet" method="post">
                                    <input type="hidden" name="file" value="${fileMap.get(task.name)}"/>
                                    <input type="submit" value="Delete" class="btn btn-outline-danger"/>
                                </form>
                                <c:set var="file" value="${fileMap.get(task.name)}" scope="page"/>
                            </section>
                            <footer class="footer">
                                <a href="#" class="btn">close</a>
                            </footer>
                        </aside>
                    </c:if>


                    <c:if test="${fileMap.get(task.name) == null}">
                        <a href="#modalWindow">No file</a>
                        <aside class="modalWindow" id="modalWindow">
                            <header>
                                <h2>Choose file action</h2>
                            </header>
                            <section>
                                <form action="UploadFileServlet" method="post" enctype="multipart/form-data">
                                    <input name="file" type="file" value="Upload"/>
                                    <input type="submit" value="Upload" class="btn btn-outline-danger">
                                </form>
                                <c:set var="file" value="No file" scope="page"/>
                            </section>
                            <footer class="footer">
                                <a href="#" class="btn">close</a>
                            </footer>
                        </aside>
                    </c:if>

                    <%--<label class="holder">--%>
                        <%--<c:if test="${fileMap.get(task.name) != null}">--%>
                            <%--${fileMap.get(task.name)}--%>
                            <%--<div class="block">--%>
                                <%--<form action="DownloadFileServlet" method="post">--%>
                                    <%--<input type="hidden" name="file" value="${fileMap.get(task.name)}"/>--%>
                                    <%--<input type="submit" value="Download" class="btn btn-outline-danger"/>--%>
                                <%--</form>--%>
                                <%--<form action="DeleteFileServlet" method="post">--%>
                                    <%--<input type="hidden" name="file" value="${fileMap.get(task.name)}"/>--%>
                                    <%--<input type="submit" value="Delete" class="btn btn-outline-danger"/>--%>
                                <%--</form>--%>
                            <%--</div>--%>
                            <%--<c:set var="file" value="${fileMap.get(task.name)}" scope="page"/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${fileMap.get(task.name) == null}">--%>
                            <%--No file--%>
                            <%--<div class="block">--%>
                                <%--<form action="UploadFileServlet" method="post" enctype="multipart/form-data">--%>
                                    <%--<input name="file" type="file" value="Upload"/>--%>
                                    <%--<input type="submit" value="Upload" class="btn btn-outline-danger">--%>
                                <%--</form>--%>
                            <%--</div>--%>
                            <%--<c:set var="file" value="No file" scope="page"/>--%>
                        <%--</c:if>--%>
                    <%--</label>--%>
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
                        <!-- Нет того, что я просил. Нужны чек боксы, которые будут появляться в корзине -->
                    </c:if>
                </td>
            </tr>
            <tr>
                    ${task.description}
            </tr>
        </c:forEach>
        <c:if test="${isBasket && conferences != null}">
            <input type="submit" value=&#9760 form="basketForm"/>
        </c:if>
        <!-- Где-то тут стоит расположить кнопки, которые будут появляться в корзине. Кнопка полного удаления,
         восстановления, которые будут связаны с чекбоксами. Будут проблемы, спрашивай. Я объсню, как сделать-->
    </table>

</div>