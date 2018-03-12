package by.gsu.epamlab.factories;

import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

public class TaskDAOFactory {
    private static ITaskDAO iTaskDAO;

    private enum Implementation {

        DATABASE {
            @Override
            void setImplementation() {

                iTaskDAO = TaskDatabaseImplementation.getTaskDarabaseImplementation();
            }
        };


        abstract void setImplementation();

    }

    public static void setTaskDAO(String implementationName) {

        if(iTaskDAO == null) {

            Implementation.valueOf(implementationName).setImplementation();

        }

    }

    public static ITaskDAO getTaskDAOFromFactory() {
        return iTaskDAO;
    }
}
