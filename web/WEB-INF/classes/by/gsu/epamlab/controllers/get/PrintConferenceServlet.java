package by.gsu.epamlab.controllers.get;

import by.gsu.epamlab.controllers.AbstractBaseController;
import by.gsu.epamlab.controllers.enums.ConnectionType;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrintConferenceServlet extends AbstractBaseController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            String connectionType = request.getParameter("from");
            if(connectionType == null)
                connectionType = "no_ajax";
            ConnectionType.valueOf(connectionType.toUpperCase()).conferenceResponse(request, response);

        } catch (DaoException e) {
            e.printStackTrace();
            System.out.println("dsdsdsd");
        }
    }


}
