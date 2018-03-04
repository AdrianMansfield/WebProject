package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEventServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String [] eventIds = request.getParameterValues("events");
            IConferenceDAO iConferenceDAO = new ConferenceDatabaseImplementation();
            iConferenceDAO.removeEvents(eventIds);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

}
