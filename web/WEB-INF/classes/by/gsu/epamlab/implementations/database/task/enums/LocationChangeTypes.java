package by.gsu.epamlab.implementations.database.task.enums;

import by.gsu.epamlab.constants.database.TaskFunctionConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.interfaces.ITaskDAO;

public enum LocationChangeTypes {
    MAIN {

        @Override
        public String getMoveTaskQuery() {

            return TaskFunctionConstants.SQL_THROW_INTO_MAIN;

        }

    }, FIXED {

        @Override
        public String getMoveTaskQuery() {

            return TaskFunctionConstants.SQL_THROW_INTO_FIXED;

        }

    }, BASKET {

        @Override
        public String getMoveTaskQuery() {

            return TaskFunctionConstants.SQL_THROW_INTO_BASKET;

        }

    };

    public abstract String getMoveTaskQuery();

}
