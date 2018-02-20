package by.gsu.epamlab.factories;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.implementations.UserDatabaseImplementation;
import by.gsu.epamlab.implementations.UserRAMImplementation;

public class UserDAOFactory {
    private enum Implementation {
        RAM {
            @Override
            IUserDAO getImplementation() {
                return new UserRAMImplementation();
            }
        }, DATABASE {
            @Override
            IUserDAO getImplementation() {
                return new UserDatabaseImplementation();
            }
        };

        abstract IUserDAO getImplementation();
    }

    public static IUserDAO getUserDAOFromFactory(String implementationName) {
        return Implementation.valueOf(implementationName).getImplementation();
    }
}
