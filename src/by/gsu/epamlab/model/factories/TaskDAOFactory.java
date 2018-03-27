package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.implementations.database.task.TaskDatabaseImplementation;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.control.listeners.ApplicationContextParameter;

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
