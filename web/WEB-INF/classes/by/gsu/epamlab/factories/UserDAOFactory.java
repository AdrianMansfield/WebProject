package by.gsu.epamlab.factories;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.implementations.UserDatabaseImplementation;
import by.gsu.epamlab.implementations.UserRAMImplementation;

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

    public static void setUserDAO(String implementationName) {
        if(iUserDAO == null) {
            Implementation.valueOf(implementationName).setImplementation();
        }
    }

    public static IUserDAO getUserDAOFromFactory() {
        return iUserDAO;
    }
}
