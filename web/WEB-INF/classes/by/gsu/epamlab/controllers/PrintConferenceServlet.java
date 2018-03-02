package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.Conference;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.enums.ConferenceTypes;
import by.gsu.epamlab.controllers.enums.ConnectionType;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PrintConferenceServlet extends AbstractBaseController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            String connectionType = request.getParameter("from");
            if(connectionType == null)
                connectionType = "form";
            ConnectionType.valueOf(connectionType.toUpperCase()).responseToClient(request, response);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


}
