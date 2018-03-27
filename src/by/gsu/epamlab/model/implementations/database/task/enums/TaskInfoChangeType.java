package by.gsu.epamlab.model.implementations.database.task.enums;

import by.gsu.epamlab.model.implementations.database.constants.TaskInfoChangeConstants;

public enum TaskInfoChangeType {

    DESCRIPTION{
        @Override
        public String getChangeTaskInfoQuery() {
            return TaskInfoChangeConstants.SQL_CHANGE_DESCRIPTION;
        }
    }, DATE{
        @Override
        public String getChangeTaskInfoQuery() {
            return TaskInfoChangeConstants.SQL_CHANGE_DATE;
        }
    };

    public abstract String getChangeTaskInfoQuery();
}
