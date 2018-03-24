package by.gsu.epamlab.model.implementations.database.task.enums;

import by.gsu.epamlab.constants.database.SelectTasksConstants;

import java.sql.Connection;


public enum SelectTaskTypes {

    TODAY   {

        @Override
        public String getSelectTaskQuery()  {

            return SelectTasksConstants.SQL_SELECT_TODAY_TASKS;

        }

    }, TOMORROW {

        @Override
        public String getSelectTaskQuery()  {

            return SelectTasksConstants.SQL_SELECT_TOMORROW_TASKS;

        }


    }, SOMEDAY  {

        @Override
        public String getSelectTaskQuery()  {

            return SelectTasksConstants.SQL_SELECT_SOMEDAY_TASKS;

        }

    }, FIXED {

        @Override
        public String getSelectTaskQuery()  {

            return SelectTasksConstants.SQL_SELECT_FIXED_TASKS;

        }
    }, BASKET {

        @Override
        public String getSelectTaskQuery()  {

            return SelectTasksConstants.SQL_SELECT_BASKET_TASKS;

        }
    };

    public abstract String getSelectTaskQuery();



}
