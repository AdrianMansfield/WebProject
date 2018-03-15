package by.gsu.epamlab.control.post.task;

import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.FileOperations;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.task.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DeleteTaskServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            String [] taskIds = request.getParameterValues(ParameterConstants.TASK_IDS_PARAMETER);

            List<Task> taskList =  iTaskDAO.getTasksById(userId, taskIds);

            String userLogin = (String) request.getSession().getAttribute(ParameterConstants.LOGIN_PARAMETER);

            FileOperations.deleteFiles(userId, taskList, userLogin);

            iTaskDAO.removeTasks(taskIds);

            sendRedirectToPrintTaskServlet(request, response);

        } catch (DaoException e) {

            e.printStackTrace();

        }
    }
}