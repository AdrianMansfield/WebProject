<head>
    <link rel="stylesheet" href="../../css/addConferences.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker({
                dateFormat: "dd.mm.yy",
                minDate: 0
            });
        } );
    </script>
</head>
<body>
<form action="AddTaskServlet" method = "post" enctype="multipart/form-data">
    <p><input type="radio" value="today" name="dateType" id = "today" checked="checked"/><label for="today">Today</label>
    <input type="radio" value="tomorrow" name="dateType" id = "tomorrow"/><label for="tomorrow">Tomorrow</label>
    <input type="radio" value="someday" name="dateType" id = "someday"/><label for="someday">Someday</label></p>
    Name:	<input name="taskName" required/>
    <p>Department:	<input name="department" required/></p>
    <p>Date: <input type="text" id="datepicker" name="date"
                    pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" data-valid-example="24.02.2018" placeholder="dd.mm.yyyy"/></p>
    <p>Attach task file:	<input type="file" name="file"/></p>
    <p><input type="submit" value="Send"></p>
</form>
</body>
