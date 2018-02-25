package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.Conference;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.enums.ConferenceTypes;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class MainServlet extends AbstractBaseController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            HttpSession session = request.getSession();
            session.removeAttribute(Constants.CONFERENCE_LIST_NAME);
            session.removeAttribute(Constants.FILE_MAP_PARAMETER);
            String dateType = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            if(dateType == null) {
                dateType = "TODAY"; // CORRECT
            }
            dateType = dateType.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Conference> taskList = ConferenceTypes.valueOf(dateType).getTasks(id);
            session.setAttribute(Constants.CONFERENCE_LIST_NAME, taskList);
            session.setAttribute(Constants.FILE_MAP_PARAMETER, FileOperations.getFileForTask(request));
            response.sendRedirect(Constants.MAIN_URL);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


}
