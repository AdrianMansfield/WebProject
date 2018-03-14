package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.controllers.AbstractBaseController;
import by.gsu.epamlab.exceptions.NonHttpMethodException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract  class AbstractNonGetController extends AbstractBaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(response.SC_BAD_REQUEST, ExceptionConstants.NON_GET_EXCEPTION_MESSAGE); //will check this function
        throw new NonHttpMethodException(ExceptionConstants.NON_GET_EXCEPTION_MESSAGE);
    }
}
