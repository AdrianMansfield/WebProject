package by.gsu.epamlab.implementations.database.task.enums;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.Date;
import java.util.Calendar;

public enum AddTaskDateTypes {
    TODAY  {

        private Date getTodayDate() {
            Calendar calendar = Calendar.getInstance();

            return new Date(calendar.getTime().getTime());

        }

        @Override
        public void setTaskDate(Task task) throws DaoException {

            task.setDate(getTodayDate());

        }
    }, TOMORROW {


        private Date getTomorrowDay() {

            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);

            return new Date(calendar.getTime().getTime());

        }

        @Override
        public void setTaskDate(Task task) throws DaoException {

            task.setDate(getTomorrowDay());

        }

    }, SOMEDAY  {

        @Override
        public void setTaskDate(Task task) throws DaoException {

        }

    };

    public abstract void setTaskDate(Task task) throws DaoException;


}
