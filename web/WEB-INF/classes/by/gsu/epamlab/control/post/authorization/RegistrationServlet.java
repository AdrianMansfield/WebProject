package by.gsu.epamlab.control.post.authorization;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.UserDAOFactory;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends AbstractAuthorizationController {


    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String login = request.getParameter(ParameterConstants.LOGIN_PARAMETER);

            String password = request.getParameter(ParameterConstants.PASSWORD_PARAMETER);

            if(!goodValues(login, password)) {

                jumpError(UrlConstants.REGISTRATION_URL, Constants.EMPTY_DATA, request, response);

                return;

            }

            IUserDAO iUserDAO = UserDAOFactory.getUserDAOFromFactory();

            boolean isAdded = iUserDAO.setUser(login, password);

            if(isAdded) {

                jumpPage(UrlConstants.LOGIN_SERVLET_URL, request, response);

            }
            else {

                jumpError(UrlConstants.REGISTRATION_URL, ErrorConstants.INVALID_LOGIN_OR_PASSWORD_ERROR, request, response);

            }
        } catch (DaoException e) {

            jumpError(UrlConstants.REGISTRATION_URL, ErrorConstants.SERVER_ERROR, request, response);

        }
    }
}
