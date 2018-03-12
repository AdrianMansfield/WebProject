<header>
    <h2>Add task</h2>
</header>
<section>
<div class="form-group">
    <form action="AddTaskServlet" method="post" enctype="multipart/form-data">
        <p>
            <input type="radio" value="today" name="taskType" id="today" checked="checked"/>
            <label for="today">Today</label>
            <input type="radio" value="tomorrow" name="taskType" id="tomorrow"/>
            <label for="tomorrow">Tomorrow</label>
            <input type="radio" value="someday" name="taskType" id="someday"/>
            <label for="someday">Someday</label>
        </p>
        Name:<input name="taskName" class="form-control" required/>
        Description: <input name="description" class="form-control" required/>
        Date: <input type="text" id="datepicker" class="form-control" name="date"
                     pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" data-valid-example="24.02.2018"
                     placeholder="dd.mm.yyyy"/>
        Attach task file: <input type="file" name="file" class="form-control-file mb-1"/>
        <input type="submit" value="Add" class=" btn btn-outline-danger float-left btn-sm">
    </form>
</div>
</section>
<footer class="footer">
    <a href="#">close</a>
</footer>
