package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.model.user.Role;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CookieValidationFilter extends AbstractFilter {

    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();

        HttpSession session = request.getSession();

        String control = JspConstants.STARTING_PAGE_FOR_VISITOR;

        if(cookies != null) {

            Cookie idCookie = null;
            Cookie loginCookie = null;
            Cookie roleCookie = null;

            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                switch (name) {

                    case ParameterConstants.USER_ID_PARAMETER : idCookie = cookie;
                                                                break;

                    case ParameterConstants.LOGIN_PARAMETER :   loginCookie = cookie;
                                                                break;

                    case ParameterConstants.ROLE_PARAMETER :    roleCookie = cookie;
                                                                break;
                }
            }


            if (idCookie != null && loginCookie != null && roleCookie != null) {

                String id = idCookie.getValue();

                String login = loginCookie.getValue();

                String role = roleCookie.getValue();

                if(!badValues(id, login) && (Role.USER.toString().equals(role) || Role.ADMIN.toString().equals(role))) {
                    session.setAttribute(ParameterConstants.USER_ID_PARAMETER, id);
                    session.setAttribute(ParameterConstants.LOGIN_PARAMETER, login);
                    session.setAttribute(ParameterConstants.ROLE_PARAMETER, role);
                    control = JspConstants.STARTING_PAGE_FOR_USER;
                }

            }
        }

        session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, control);

        request.getRequestDispatcher(UrlConstants.INDEX_URL).forward(request, response);

    }

}
