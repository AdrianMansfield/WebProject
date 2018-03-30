package by.gsu.epamlab.control.servlets.get;

import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.control.AbstractBaseController;
import by.gsu.epamlab.exceptions.NonHttpMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractNonPostController extends AbstractBaseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        throw new NonHttpMethodException(ExceptionConstants.NON_POST_EXCEPTION_MESSAGE);

    }
}
