package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.Date;
import java.util.Calendar;

public enum TaskAddTypes {
    TODAY  {

        private Date getTodayDate() {
            Calendar calendar = Calendar.getInstance();
            return new Date(calendar.getTime().getTime());
        }

        @Override
        public void addTask(String userId, Task task) throws DaoException {
            task.setDate(getTodayDate());
            I_TASK_DAO.addTask(userId, task);
        }
    }, TOMORROW {


        private Date getTomorrowDay() {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);
            return new Date(calendar.getTime().getTime());
        }

        @Override
        public void addTask(String userId, Task task) throws DaoException {
            task.setDate(getTomorrowDay());
            I_TASK_DAO.addTask(userId, task);
        }

    }, SOMEDAY  {

        @Override
        public void addTask(String userId, Task task) throws DaoException {
            I_TASK_DAO.addTask(userId, task);
        }

    };


    protected static final ITaskDAO I_TASK_DAO = new TaskDatabaseImplementation();

    public abstract void addTask(String userId, Task task) throws DaoException;


}
