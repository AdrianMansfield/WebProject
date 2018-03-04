package by.gsu.epamlab.controllers.get;

import by.gsu.epamlab.controllers.AbstractBaseController;
import by.gsu.epamlab.controllers.enums.ConnectionType;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrintEventServlet extends AbstractBaseController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String connectionType = request.getParameter("from");
            if(connectionType == null)
                connectionType = "no_ajax";
            ConnectionType.valueOf(connectionType.toUpperCase()).eventResponse(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
