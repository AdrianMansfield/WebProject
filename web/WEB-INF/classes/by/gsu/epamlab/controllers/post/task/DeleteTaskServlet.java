package by.gsu.epamlab.controllers.post.task;

import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteTaskServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String [] taskIds = request.getParameterValues(ParameterConstants.TASK_IDS_PARAMETER);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            iTaskDAO.removeTasks(taskIds);

            request.getRequestDispatcher(UrlConstants.DELETE_SERVLET_URL).forward(request, response);

        } catch (DaoException e) {

            e.printStackTrace();

        }
    }
}
