package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEventServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String conferenceId = (String) request.getSession().getAttribute("conferenceId");
            String eventName = request.getParameter("eventName");
            String eventTime = request.getParameter("eventTime");
            Event event = new Event(Constants.ZERO, eventName, eventTime);
            IConferenceDAO iConferenceDAO = new ConferenceDatabaseImplementation();
            iConferenceDAO.addEvent(conferenceId, event);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
