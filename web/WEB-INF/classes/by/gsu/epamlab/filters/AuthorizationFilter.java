package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        Cookie idCookie = null;
        Cookie loginCookie = null;
        Cookie roleCookie = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ParameterConstants.USER_ID_PARAMETER)) {
                    idCookie = cookie;
                }
                if (cookie.getName().equals(ParameterConstants.LOGIN_PARAMETER)) {
                    loginCookie = cookie;
                }
                if (cookie.getName().equals(ParameterConstants.ROLE_PARAMETER)) {
                    roleCookie = cookie;
                }
            }
            if (idCookie != null && loginCookie != null && roleCookie != null) {
                session.setAttribute(ParameterConstants.USER_ID_PARAMETER, idCookie.getValue());
                session.setAttribute(ParameterConstants.LOGIN_PARAMETER, loginCookie.getValue());
                session.setAttribute(ParameterConstants.ROLE_PARAMETER, roleCookie.getValue());
                session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_USER_PATH);
            }
            else {
                session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_VISITOR_PATH);
            }
        }
        request.getRequestDispatcher(UrlConstants.INDEX_URL).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
