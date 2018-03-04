<form action="PrintConferenceServlet" method="post" class="my-0 mr-md-0 mb-1">
    <button class="btn btn-danger" name="dateType" value="today" onclick="sendQueryToPrintConferenceServlet(value); showConference();return false" >Today</button>
    <button class="btn btn-danger" name="dateType" value="tomorrow" onclick="sendQueryToPrintConferenceServlet(value); showConference();return false" >Tomorrow</button>
    <button class="btn btn-danger" name="dateType" value="someday" onclick="sendQueryToPrintConferenceServlet(value); showConference();return false" >Someday</button>
    <button class="btn btn-danger" name="dateType" value="fixed" onclick="sendQueryToPrintConferenceServlet(value);showConference();return false" >Fixed</button>
    <button class="btn btn-danger" name="dateType" value="basket" onclick="sendQueryToPrintConferenceServlet(value);showConference();return false" >Basket</button>
    <input type="hidden" name = "from" value="no_ajax"/>
</form>
