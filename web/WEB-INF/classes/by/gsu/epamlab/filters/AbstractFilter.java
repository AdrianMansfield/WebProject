package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filter(servletRequest, servletResponse, filterChain);
    }

    @Override
    public void destroy() {

    }

    protected abstract void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    protected void jumpError(String url, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

        requestDispatcher.forward(request, response);

    }


    protected boolean badValues(String ... values) {
        boolean good = false;

        for(String value : values) {

            if(value == null || value.isEmpty()) {

                good = true;

                break;

            }
        }

        return good;
    }
}
