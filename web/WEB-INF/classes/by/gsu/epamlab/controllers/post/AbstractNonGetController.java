package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.AbstractBaseController;
import by.gsu.epamlab.exceptions.NonGetException;
import by.gsu.epamlab.factories.UserDAOFactory;
import by.gsu.epamlab.interfaces.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract  class AbstractNonGetController extends AbstractBaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new NonGetException(Constants.NON_GET_EXCEPTION_MESSAGE);
    }
}
