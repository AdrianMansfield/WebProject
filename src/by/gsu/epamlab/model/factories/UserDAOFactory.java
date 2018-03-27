package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.implementations.database.user.UserDatabaseImplementation;
import by.gsu.epamlab.model.implementations.ram.user.UserRAMImplementation;
import by.gsu.epamlab.control.listeners.ApplicationContextParameter;

public class UserDAOFactory {

    private static IUserDAO iUserDAO;

    private enum Implementation {
        RAM {

            @Override
            void setImplementation() {
                iUserDAO = UserRAMImplementation.getUserRAMImplementation();
            }

        }, DATABASE {

            @Override
            void setImplementation() {
                iUserDAO = UserDatabaseImplementation.getUserRAMImplementation();
            }

        };

        abstract void setImplementation();
    }

    public static IUserDAO getUserDAOFromFactory() {

        if(iUserDAO == null) {

            Implementation.valueOf(ApplicationContextParameter.getUserImplementationName()).setImplementation();

        }

        return iUserDAO;
    }
}
