package by.gsu.epamlab.control.filters.file;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.control.filters.AbstractFilter;
import by.gsu.epamlab.model.FileOperations;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Part;

public class UploadFileFilter extends AbstractFilter {
    private static final String EXE_FORMAT = "EXE";
    private static final String JSP_FORMAT = "HTML";
    private static final String HTML_FORMAT = "JS";
    private static final String JS_FORMAT = "JSP";
    private static final String DOT = "\\.";

    @Override
    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Part part = request.getPart(ParameterConstants.FILE_PARAMETER);

        String fileName = FileOperations.extractFileName(part);

        if(Constants.EMPTY_STRING.equals(fileName)) {
            jumpError(UrlConstants.MAIN_URL, ErrorConstants.DID_NOT_CHOOSE_FILE_FOR_UPLOAD_ERROR, request, response);
        }
        else {

            String [] fileNameParts = fileName.split(DOT);

            if(fileNameParts.length>0) {
                String fileFormat = fileNameParts[fileNameParts.length-1];

                fileFormat = fileFormat.toUpperCase();

                switch (fileFormat) {
                    case EXE_FORMAT  :
                    case JS_FORMAT   :
                    case HTML_FORMAT :
                    case JSP_FORMAT  :   jumpError(UrlConstants.MAIN_URL, ErrorConstants.FILE_FORMAT_ERROR, request, response);
                                         return;
                }
            }



            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
