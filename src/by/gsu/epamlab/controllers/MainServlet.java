package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.controllers.enums.TaskTypes;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class MainServlet extends AbstractBaseController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            HttpSession session = request.getSession();
            session.removeAttribute(Constants.TASK_LIST_NAME);
            String date = request.getParameter(Constants.DATE_PARAMETER);
            if(date == null) {
                date = "TODAY"; // CORRECT
            }
            List<Task> taskList = TaskTypes.valueOf(date.toUpperCase()).getTasks((String)session.getAttribute(Constants.ID));
            session.setAttribute(Constants.TASK_LIST_NAME, taskList);
            response.sendRedirect(Constants.MAIN_URL);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
