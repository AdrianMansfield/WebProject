package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitServlet extends AbstractBaseController {


    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        setCookie(response, Constants.ID, Constants.EMPTY_STRING, Constants.ZERO);
        setCookie(response, Constants.ROLE, Constants.EMPTY_STRING, Constants.ZERO);
        setCookie(response, Constants.LOGIN, Constants.EMPTY_STRING, Constants.ZERO);
        session = request.getSession();
        session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_VISITOR_PATH);
        response.sendRedirect(Constants.INDEX_URL);
    }
}
