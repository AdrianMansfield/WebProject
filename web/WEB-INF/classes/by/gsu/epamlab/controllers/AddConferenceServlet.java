package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.Conference;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.enums.ConferenceTypes;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddConferenceServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String taskType = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            String userId = (String) request.getSession().getAttribute(Constants.ID);
            String conferenceName = request.getParameter(Constants.CONFERENCE_NAME_PARAMETER);
            String department = request.getParameter(Constants.DEPARTMENT_PARAMETER);
            String date = request.getParameter(Constants.DATE_PARAMETER);
            if(!goodValues(userId, conferenceName, department)) {
                jumpError(Constants.MAIN_URL, Constants.ADD_CONFERENCE_ERROR_MESSAGE, request, response);
                return;
            }
            Date usualDate = null;
            FileOperations.uploadFile(request, conferenceName);
            if(date != null && !Constants.EMPTY_STRING.equals(date)) {
                usualDate = new Date(new SimpleDateFormat(Constants.PRINT_DATE_FORMAT)
                        .parse(date).getTime());
            }
            Conference conference = new Conference(Constants.ZERO, conferenceName, department, usualDate);
            ConferenceTypes.valueOf(taskType.toUpperCase()).addTask(userId, conference);
            jumpPage(Constants.MAIN_SERVLET_URL, request, response);
        } catch (DaoException | ParseException e) {
            e.printStackTrace();
        }
    }



}
