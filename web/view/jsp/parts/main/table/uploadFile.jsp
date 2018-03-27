<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Form for upload file -->
<c:if test = "${fileExists}">
    <aside class="modalWindow left-side" id = "${taskId}">
        <header>
            <h2>Upload file</h2>
        </header>
        <section>
            <form action="UploadFileServlet" method="post" enctype="multipart/form-data" class="mb-0">
                <input type="hidden" name="taskType" value = "${taskType}"/>
                <input type="hidden" name="taskName" value = "${taskName}"/>
                <input name="file" type="file" value="Upload" class="form-control-file mb-1"
                       style="overflow: hidden"/>
                <input type="submit" value="Upload" class="btn btn-outline-danger btn-sm">
            </form>
        </section>
        <footer class="footer">
            <a href="#">close</a>
        </footer>
    </aside>
</c:if>
