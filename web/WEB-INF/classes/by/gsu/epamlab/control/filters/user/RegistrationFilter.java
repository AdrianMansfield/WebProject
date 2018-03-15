package by.gsu.epamlab.control.filters.user;

import by.gsu.epamlab.constants.UrlConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class RegistrationFilter extends AbstractCheckUserFilter {

    @Override
    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        checkAttributesFilter(servletRequest, servletResponse, filterChain, UrlConstants.REGISTRATION_URL);

    }
}
