package by.gsu.epamlab.controllers.filters;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.JspConstants;

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
                if (cookie.getName().equals(Constants.ID)) {
                    idCookie = cookie;
                }
                if (cookie.getName().equals(Constants.LOGIN)) {
                    loginCookie = cookie;
                }
                if (cookie.getName().equals(Constants.ROLE)) {
                    roleCookie = cookie;
                }
            }
            if (idCookie != null && loginCookie != null && roleCookie != null) {
                session.setAttribute(Constants.ID, idCookie.getValue());
                session.setAttribute(Constants.LOGIN, loginCookie.getValue());
                session.setAttribute(Constants.ROLE, roleCookie.getValue());
                session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_USER_PATH);
            }
            else {
                session.setAttribute(JspConstants.ATTRIBUTE_CONTROL, JspConstants.STARTING_PAGE_FOR_VISITOR_PATH);
            }
        }
        request.getRequestDispatcher(Constants.INDEX_URL).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
