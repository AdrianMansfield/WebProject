package by.gsu.epamlab.controllers.post;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.controllers.enums.ConferenceLocationTypes;
import by.gsu.epamlab.controllers.enums.ConnectionType;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MoveConferenceServlet extends AbstractNonGetController {
    
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String connectionType = request.getParameter("from");
            if(connectionType == null)
                connectionType = "no_ajax";
            ConnectionType.valueOf(connectionType.toUpperCase()).moveConferenceResponse(request, response);
            
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
