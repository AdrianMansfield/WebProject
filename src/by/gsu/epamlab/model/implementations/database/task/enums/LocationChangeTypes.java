package by.gsu.epamlab.model.implementations.database.task.enums;

import by.gsu.epamlab.constants.database.TaskFunctionConstants;

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
