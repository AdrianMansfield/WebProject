package by.gsu.epamlab.model.implementations.database.task.enums;

import by.gsu.epamlab.constants.database.TaskInfoChangeConstants;

public enum TaskInfoChangeType {

    NAME{
        @Override
        public String getChangeTaskInfoQuery() {
            return TaskInfoChangeConstants.SQL_CHANGE_NAME;
        }
    }, DESCRIPTION{
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
