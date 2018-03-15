package by.gsu.epamlab.control.filters.file;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.control.filters.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Part;

public class UploadFileFilter extends AbstractFilter {

    @Override
    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Part part = request.getPart(ParameterConstants.FILE_PARAMETER);

        String fileName = part.getSubmittedFileName();

        if(Constants.EMPTY_STRING.equals(fileName)) {
            jumpError(UrlConstants.MAIN_URL, ErrorConstants.DID_NOT_CHOOSE_FILE_FOR_UPLOAD_ERROR, request, response);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
