package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.factories.UserDAOFactory;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public abstract class AbstractBaseController extends HttpServlet {
    protected IUserDAO iUserDAO;

    @Override
    public void init() throws ServletException {
        String implementationName = getServletContext().getInitParameter(Constants.IMPLEMENTATION);
        iUserDAO = UserDAOFactory.getUserDAOFromFactory(implementationName);
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


    protected void setCookie(HttpServletResponse response, String name, String parameter, int age) {
        Cookie cookie = new Cookie(name, parameter);
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }

    protected void setUserAuthorization(User user, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.ID, String.valueOf(user.getId()));
        session.setAttribute(Constants.LOGIN, user.getName());
        session.setAttribute(Constants.ROLE, Role.USER.toString());
        session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_USER_PATH);
        setCookie(response, Constants.ID, String.valueOf(user.getId()), Constants.COOKIE_AGE);
        setCookie(response, Constants.LOGIN, user.getName(), Constants.COOKIE_AGE);
        setCookie(response, Constants.ROLE, Role.USER.toString(), Constants.COOKIE_AGE);
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
