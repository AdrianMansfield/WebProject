<form action="PrintConferenceServlet" method="post" class="my-0 mr-md-0 mb-1">
    <button class="btn btn-danger" name="dateType" value="today" onclick="sendQueryToMainServlet(value);return false" >Today</button>
    <button class="btn btn-danger" name="dateType" value="tomorrow" onclick="sendQueryToMainServlet(value);return false" >Tomorrow</button>
    <button class="btn btn-danger" name="dateType" value="someday" onclick="sendQueryToMainServlet(value);return false" >Someday</button>
    <input type="hidden" name = "from" value="no_ajax"/>
</form>
