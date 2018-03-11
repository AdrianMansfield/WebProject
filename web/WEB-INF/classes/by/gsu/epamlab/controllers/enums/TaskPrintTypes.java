package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public enum TaskPrintTypes {
    TODAY   {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return I_TASK_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_TODAY_TASKS, getTomorrowDay());
        }

    }, TOMORROW {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return I_TASK_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_TOMORROW_TASKS, getTomorrowDay());
        }


    }, SOMEDAY  {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return I_TASK_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_SOMEDAY_TASKS, getTomorrowDay());
        }

    }, FIXED {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return I_TASK_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_FIXED_TASKS);
        }
    }, BASKET {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return I_TASK_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_BASKET_TASKS);
        }
    };

    protected static final ITaskDAO I_TASK_DAO = new TaskDatabaseImplementation();


    public abstract List<Task> getTasks(String userId) throws DaoException;


    protected Date getTomorrowDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);
        return new Date(calendar.getTime().getTime());
    }
}
