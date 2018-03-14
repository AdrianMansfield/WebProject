package by.gsu.epamlab.control.post.authorization;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitServlet extends AbstractAuthorizationController {


    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        setCookie(response, ParameterConstants.USER_ID_PARAMETER, Constants.EMPTY_STRING, Constants.ZERO);
        setCookie(response, ParameterConstants.ROLE_PARAMETER, Constants.EMPTY_STRING, Constants.ZERO);
        setCookie(response, ParameterConstants.LOGIN_PARAMETER, Constants.EMPTY_STRING, Constants.ZERO);
        session = request.getSession();
        session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_VISITOR_PATH);
        response.sendRedirect(UrlConstants.INDEX_URL);
    }
}
