package by.gsu.epamlab.controllers.post;


import by.gsu.epamlab.beans.user.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends AbstractNonGetController {


    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        if(!goodValues(login, password)) {
            jumpError(Constants.REGISTRATION_URL, Constants.EMPTY_DATA, request, response);
            return;
        }
        try {
            User user = iUserDAO.setUser(login, password);
            if(user != null) {
                setUserAuthorization(user, request, response);
                jumpPage(Constants.MAIN_URL, request, response);
            }
            else {
                jumpError(Constants.REGISTRATION_URL, Constants.INVALID_LOGIN_OR_PASSWORD, request, response);
            }
        } catch (DaoException e) {
            System.err.println(e);
            jumpError(Constants.REGISTRATION_URL, Constants.SERVER_ERROR, request, response);
        }
    }
}