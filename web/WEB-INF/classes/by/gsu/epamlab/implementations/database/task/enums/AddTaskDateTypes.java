package by.gsu.epamlab.implementations.database.task.enums;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.Constants;

import java.sql.Date;
import java.util.Calendar;

public enum AddTaskDateTypes {
    TODAY  {

        private Date getTodayDate() {
            Calendar calendar = Calendar.getInstance();

            return new Date(calendar.getTime().getTime());

        }

        @Override
        public void setTaskDate(Task task) {

            task.setDate(getTodayDate());

        }
    }, TOMORROW {


        private Date getTomorrowDay() {

            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);

            return new Date(calendar.getTime().getTime());

        }

        @Override
        public void setTaskDate(Task task) {

            task.setDate(getTomorrowDay());

        }

    }, SOMEDAY  {

        @Override
        public void setTaskDate(Task task)  {

        }

    };

    public abstract void setTaskDate(Task task);


}
