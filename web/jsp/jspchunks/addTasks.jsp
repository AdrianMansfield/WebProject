<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
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
    Name:	<input name="taskName" required/>
    <p>Department:	<input name="department" required/>
    <p>Date: <input type="text" id="datepicker" name="date"></p>
    <p>Attach task file:	<input type="file" name="file" required/>
    <p><input type="submit" value="Send">
</form>
</body>
