<form action="PrintTaskServlet" method="get" class="my-0 mr-md-0 mb-1">
    <button class="btn" name="taskType" value="today" onclick="sendQueryToPrintTaskServlet(value);return false" >Today</button>
    <button class="btn" name="taskType" value="tomorrow" onclick="sendQueryToPrintTaskServlet(value);return false" >Tomorrow</button>
    <button class="btn" name="taskType" value="someday" onclick="sendQueryToPrintTaskServlet(value);return false" >Someday</button>
    <button class="btn" name="taskType" value="fixed" onclick="sendQueryToPrintTaskServlet(value);return false" >Fixed</button>
    <button class="btn" name="taskType" value="basket" onclick="sendQueryToPrintTaskServlet(value);return false" >Basket</button>
    <input type="hidden" name = "from" value="no_ajax"/>
</form>
