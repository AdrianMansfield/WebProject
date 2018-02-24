package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public enum  TaskTypes {
    TODAY   {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return iTaskDAO.getTasks(userId, DatabaseConstants.SQL_SELECT_TODAY_TASKS, getTomorrowDay());
        }

        private Date getTodayDate() {
            Calendar calendar = Calendar.getInstance();
            return new Date(calendar.getTime().getTime());
        }

        @Override
        public void addTask(String id, String filePath, Date date) throws DaoException {
            //iTaskDAO.addTask(id, filePath, getTodayDate());
        }
    }, TOMORROW {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return iTaskDAO.getTasks(userId, DatabaseConstants.SQL_SELECT_TOMORROW_TASKS, getTomorrowDay());
        }

        @Override
        public void addTask(String id, String filePath, Date date) throws DaoException {
            //iTaskDAO.addTask(id, filePath, getTomorrowDay());
        }

    }, SOMEDAY  {

        @Override
        public List<Task> getTasks(String userId) throws DaoException {
            return iTaskDAO.getTasks(userId, DatabaseConstants.SQL_SELECT_SOMEDAY_TASKS, getTomorrowDay());
        }

        @Override
        public void addTask(String id, String filePath, Date date) throws DaoException {
            //iTaskDAO.addTask(id, filePath, date);
        }

    };

    protected static final ITaskDAO iTaskDAO = new TaskDatabaseImplementation();


    public abstract List<Task> getTasks(String userId) throws DaoException;

    public abstract void addTask(String id, String filePath, Date date) throws DaoException;

    protected Date getTomorrowDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);
        return new Date(calendar.getTime().getTime());
    }
}
