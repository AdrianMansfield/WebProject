package by.gsu.epamlab.factories;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.implementations.database.user.UserDatabaseImplementation;
import by.gsu.epamlab.implementations.ram.UserRAMImplementation;
import by.gsu.epamlab.listener.ApplicationContextParameter;

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
