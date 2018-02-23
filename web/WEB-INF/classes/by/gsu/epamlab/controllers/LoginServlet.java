package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.exceptions.DaoException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        if(isInvalidLoginAndPassword(login, password)) {
            jumpError(Constants.LOGIN_URL, Constants.EMPTY_DATA, request, response);
        }
        try {
            User user = iUserDAO.getUser(login, password);

            if(user == null) {
                jumpError(Constants.LOGIN_URL, Constants.INVALID_LOGIN_OR_PASSWORD, request, response);
                return;
            }
            setUserAuthorization(user, request, response);
            jumpPage(Constants.MAIN_SERVLET_URL, request, response);
        } catch (DaoException e) {
            e.printStackTrace();
            jumpError(Constants.LOGIN_URL, Constants.SERVER_ERROR, request, response);
        }
    }
}
