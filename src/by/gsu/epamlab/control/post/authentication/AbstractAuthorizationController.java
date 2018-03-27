package by.gsu.epamlab.control.post.authentication;

import by.gsu.epamlab.model.user.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.post.AbstractNonGetController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractAuthorizationController extends AbstractNonGetController {

    protected void setCookie(HttpServletResponse response, String name, String parameter, int age) {

        Cookie cookie = new Cookie(name, parameter);

        cookie.setMaxAge(age);

        response.addCookie(cookie);

    }

    protected void setUserAuthorization(User user, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        session.setAttribute(ParameterConstants.USER_ID_PARAMETER, String.valueOf(user.getId()));

        session.setAttribute(ParameterConstants.LOGIN_PARAMETER, user.getName());

        session.setAttribute(ParameterConstants.ROLE_PARAMETER, user.getRole().toString());

        session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_USER);

        setCookie(response, ParameterConstants.USER_ID_PARAMETER, String.valueOf(user.getId()), Constants.COOKIE_AGE);

        setCookie(response, ParameterConstants.LOGIN_PARAMETER, user.getName(), Constants.COOKIE_AGE);

        setCookie(response, ParameterConstants.ROLE_PARAMETER, user.getRole().toString(), Constants.COOKIE_AGE);

    }
}
