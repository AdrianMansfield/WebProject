package by.gsu.epamlab.implementations.database.task.enums;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.DateOperations;

import java.sql.Date;
import java.util.Calendar;

public enum  DateTypes {
    TODAY   {

        @Override
        public Date getDate() {
            return DateOperations.getTomorrowDay();
        }

    }, TOMORROW {

        @Override
        public Date getDate() {
            return DateOperations.getTomorrowDay();
        }

    }, SOMEDAY  {

        @Override
        public Date getDate() {
            return DateOperations.getTomorrowDay();
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


    protected Date getPastDate() {
        return new Date(Constants.ZERO);
    }

}
