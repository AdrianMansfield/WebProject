package by.gsu.epamlab.control.get;

import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.control.AbstractBaseController;
import by.gsu.epamlab.exceptions.NonHttpMethodException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractNonPostController extends AbstractBaseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(response.SC_BAD_REQUEST, ExceptionConstants.NON_POST_EXCEPTION_MESSAGE);
        throw new NonHttpMethodException(ExceptionConstants.NON_POST_EXCEPTION_MESSAGE);
    }
}
