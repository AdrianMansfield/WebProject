package by.gsu.epamlab.factories;

import by.gsu.epamlab.implementations.database.task.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.listeners.ApplicationContextParameter;

public class TaskDAOFactory {
    private static ITaskDAO iTaskDAO;

    private enum Implementation {

        DATABASE {
            @Override
            void setImplementation() {

                iTaskDAO = TaskDatabaseImplementation.getTaskDatabaseImplementation();
            }
        };


        abstract void setImplementation();

    }

    public static ITaskDAO getTaskDAOFromFactory() {
        if(iTaskDAO == null) {

            Implementation.valueOf(ApplicationContextParameter.getTaskImplementationName()).setImplementation();

        }
        return iTaskDAO;
    }
}
