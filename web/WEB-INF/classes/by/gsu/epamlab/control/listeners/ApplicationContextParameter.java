package by.gsu.epamlab.control.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextParameter implements ServletContextListener {
    private static final String USER_IMPLEMENTATION = "userImplementation";
    private static final String TASK_IMPLEMENTATION = "userImplementation";
    private static final String FILES_DIRECTORY = "filesDirectory";
    private static String userImplementation;
    private static String taskImplementation;
    private static String filesDirectory;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        userImplementation = servletContext.getInitParameter(USER_IMPLEMENTATION);
        taskImplementation = servletContext.getInitParameter(TASK_IMPLEMENTATION);
        filesDirectory = servletContext.getInitParameter(FILES_DIRECTORY);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        userImplementation = null;
        taskImplementation = null;
    }

    public static String getUserImplementationName() {
        return userImplementation;
    }

    public static String getTaskImplementationName() {
        return taskImplementation;
    }

    public static String getFilesDirectory() {
        return filesDirectory;
    }
}
