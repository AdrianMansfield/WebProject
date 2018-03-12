package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskDAOFactory;
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
        public boolean addTask(String userId, Task task) throws DaoException {

            task.setDate(getTodayDate());

            return I_TASK_DAO.addTask(userId, task);

        }
    }, TOMORROW {


        private Date getTomorrowDay() {

            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);

            return new Date(calendar.getTime().getTime());

        }

        @Override
        public boolean addTask(String userId, Task task) throws DaoException {

            task.setDate(getTomorrowDay());

            return I_TASK_DAO.addTask(userId, task);

        }

    }, SOMEDAY  {

        @Override
        public boolean addTask(String userId, Task task) throws DaoException {

            return I_TASK_DAO.addTask(userId, task);

        }

    };


    protected static final ITaskDAO I_TASK_DAO = TaskDAOFactory.getTaskDAOFromFactory();

    public abstract boolean addTask(String userId, Task task) throws DaoException;


}
