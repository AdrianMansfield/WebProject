package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteConferenceServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String [] parameters = request.getParameterValues("deleteConferenceCheck");
            String [] conferenceIds = new String[parameters.length];
            int i = 0;
            for(String parameter : parameters) {
                conferenceIds[i++] = new String(parameter.substring(Constants.ZERO, parameter.indexOf(FileConstants.FILE_DELIMITER)));
            }
            IConferenceDAO iConferenceDAO = new ConferenceDatabaseImplementation();
            iConferenceDAO.removeConferences(conferenceIds);
            request.getRequestDispatcher("/DeleteFileServlet").forward(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
