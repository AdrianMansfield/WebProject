package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

public enum TaskLocationTypes {
    MAIN {

        @Override
        public void moveTask(String taskId) throws DaoException {
            I_TASK_DAO.moveTask(taskId, DatabaseConstants.SQL_GET_FOR_MAIN);
        }

    }, FIXED {

        @Override
        public void moveTask(String taskId) throws DaoException {
            I_TASK_DAO.moveTask(taskId, DatabaseConstants.SQL_THROW_INTO_FIXED);
        }

    }, BASKET {

        @Override
        public void moveTask(String taskId) throws DaoException {
            I_TASK_DAO.moveTask(taskId, DatabaseConstants.SQL_THROW_INTO_BASKET);
        }

    };

    public abstract void moveTask(String taskId) throws DaoException;

    protected static final ITaskDAO I_TASK_DAO = new TaskDatabaseImplementation();
}
