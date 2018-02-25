package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EventServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IConferenceDAO iConferenceDAO = new ConferenceDatabaseImplementation();
        try {
            String taskId = request.getParameter(Constants.CURRENT_TASK_PARAMETER);
            HttpSession session = request.getSession();
            session.removeAttribute(Constants.EVENTS_ATTRIBUTE);
            List<Event> eventList = iConferenceDAO.getEvents(taskId);
            session.setAttribute(Constants.EVENTS_ATTRIBUTE, eventList);
            response.sendRedirect(Constants.MAIN_URL);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
