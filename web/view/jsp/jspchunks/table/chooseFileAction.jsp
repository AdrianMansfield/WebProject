<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Form for choose file action -->
<c:if test="${!fileExists}">
    <aside class="modalWindow left-side" id="${taskId}">
        <header>
            <h2>Choose file action</h2>
        </header>
        <section>
            <h5>${fileName}</h5>
            <div class="row text-center">
                <form id="${formNumber}" action="DownloadFileServlet" method="post" class="mb-0">
                    <input type="hidden" name="taskType" value="${taskType}"/>
                    <input type="hidden" name="taskId" value="${taskId}"/>
                    <input type="hidden" name="taskNames" value="${taskName}"/>
                    <input type="hidden" name="fileNames" value="${fileName}"/>
                </form>
                <div class="col">
                    <button form="${formNumber}" formaction="DownloadFileServlet"
                            class="btn btn-outline-danger">Download
                    </button>
                </div>
                <div class="col">
                    <button form="${formNumber}" formaction="DeleteFileServlet"
                            class="btn btn-outline-danger">Delete
                    </button>
                </div>
            </div>
        </section>
        <footer class="footer">
            <a href="#">close</a>
        </footer>
    </aside>
</c:if>
