package by.gsu.epamlab.controllers;


import by.gsu.epamlab.controllers.enums.PlaceType;
import by.gsu.epamlab.exceptions.DaoException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThrowBasketServlet extends AbstractNonGetController {
    
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            String whence = request.getParameter("whence");
            String [] conferenceIds = request.getParameterValues("conferences");
            PlaceType.valueOf(whence.toUpperCase()).throwConferencesToBasket(conferenceIds);
            
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
