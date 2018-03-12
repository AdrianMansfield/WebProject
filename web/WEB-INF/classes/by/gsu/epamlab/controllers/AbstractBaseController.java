package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.user.Role;
import by.gsu.epamlab.beans.user.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.factories.UserDAOFactory;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public abstract class AbstractBaseController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        String userImplementation = servletContext.getInitParameter(ParameterConstants.USER_IMPLEMENTATION_PARAMETER);
        String taskImplementation = servletContext.getInitParameter(ParameterConstants.TASK_IMPLEMENTATION_PARAMETER);
        UserDAOFactory.setUserDAO(userImplementation);
        TaskDAOFactory.setTaskDAO(taskImplementation);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        performTask(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        performTask(req, response);

    }

    protected abstract void performTask(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException;

    protected void jump(String url, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

        requestDispatcher.forward(request, response);

    }

    protected void jumpPage (String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        jump(url, Constants.EMPTY_STRING,request,response);

    }

    protected void jumpError(String url, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        jump(url, message, request, response);

    }


    protected boolean goodValues(String ... values) {
        boolean good = true;

        for(String value : values) {

            if(value == null || value.isEmpty()) {

                good = false;

                break;

            }
        }

        return good;
    }

}
