package by.gsu.epamlab.control;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public abstract class AbstractBaseController extends HttpServlet {

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

    private void jump(String url, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

        requestDispatcher.forward(request, response);

    }

    protected void jumpPage (String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        jump(url, Constants.EMPTY_STRING,request,response);

    }

    protected void jumpError(String url, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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

    protected void sendRedirectToPrintTaskServlet(HttpServletRequest request,
                                                  HttpServletResponse response) throws IOException {

        String taskType = request.getParameter(ParameterConstants.TASK_TYPE_PARAMETER);

        HttpSession session = request.getSession();

        session.setAttribute(ParameterConstants.TASK_TYPE_PARAMETER, taskType);

        response.sendRedirect(UrlConstants.PRINT_TASK_SERVLET_URL);

    }



}
