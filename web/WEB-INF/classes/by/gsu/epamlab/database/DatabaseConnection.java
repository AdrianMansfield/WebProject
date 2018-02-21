package by.gsu.epamlab.database;

import by.gsu.epamlab.constants.Constants;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final DataSource dataSource;

    static {
        DataSource temporaryDataSource = null;
        try {
            InitialContext initialContext =  new InitialContext();
            temporaryDataSource = (DataSource) initialContext.lookup(Constants.RESOURCE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        dataSource = temporaryDataSource;
        if(dataSource == null) {
            throw new RuntimeException(); // this is block requires editing
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
