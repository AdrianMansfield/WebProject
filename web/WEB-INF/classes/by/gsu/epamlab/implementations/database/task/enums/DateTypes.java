package by.gsu.epamlab.implementations.database.task.enums;

import by.gsu.epamlab.constants.Constants;

import java.sql.Date;
import java.util.Calendar;

public enum  DateTypes {
    TODAY   {

        @Override
        public Date getDate() {
            return getTomorrowDay();
        }

    }, TOMORROW {

        @Override
        public Date getDate() {
            return getTomorrowDay();
        }

    }, SOMEDAY  {

        @Override
        public Date getDate() {
            return getTomorrowDay();
        }

    }, FIXED {

        @Override
        public Date getDate() {
            return getPastDate();
        }

    }, BASKET {

        @Override
        public Date getDate() {
            return getPastDate();
        }

    };


    public abstract Date getDate();

    protected Date getTomorrowDay() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);

        return new Date(calendar.getTime().getTime());

    }

    protected Date getPastDate() {
        return new Date(Constants.ZERO);
    }

}
