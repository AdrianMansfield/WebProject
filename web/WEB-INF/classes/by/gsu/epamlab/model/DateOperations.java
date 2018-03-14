package by.gsu.epamlab.model;

import by.gsu.epamlab.constants.Constants;

import java.sql.Date;
import java.util.Calendar;

public final class DateOperations {

    private DateOperations() {}

    public static Date getTomorrowDay() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);

        return new Date(calendar.getTime().getTime());

    }

}
