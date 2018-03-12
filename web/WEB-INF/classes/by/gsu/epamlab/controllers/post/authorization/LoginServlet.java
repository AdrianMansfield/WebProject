package by.gsu.epamlab.controllers.post.authorization;

import by.gsu.epamlab.beans.user.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.exceptions.DaoException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AbstractAuthorizationController {

    @Override
    protected void performTask(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ParameterConstants.LOGIN_PARAMETER);

        String password = request.getParameter(ParameterConstants.PASSWORD_PARAMETER);

        if(!goodValues(login, password)) {

            jumpError(UrlConstants.LOGIN_URL, Constants.EMPTY_DATA, request, response);

            return;

        }
        try {
            User user = iUserDAO.getUser(login, password);

            if(user == null) {

                jumpError(UrlConstants.LOGIN_URL, Constants.INVALID_LOGIN_OR_PASSWORD, request, response);

                return;

            }
            setUserAuthorization(user, request, response);

            jumpPage(UrlConstants.MAIN_URL, request, response);

        } catch (DaoException e) {

            e.printStackTrace();

            jumpError(UrlConstants.LOGIN_URL, Constants.SERVER_ERROR, request, response);

        }
    }
}
