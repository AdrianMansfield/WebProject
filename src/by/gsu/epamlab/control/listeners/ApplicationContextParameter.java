package by.gsu.epamlab.control.listeners;

import by.gsu.epamlab.constants.JspConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextParameter implements ServletContextListener {
    private static final String USER_IMPLEMENTATION = "userImplementation";
    private static final String TASK_IMPLEMENTATION = "taskImplementation";
    private static final String FILES_DIRECTORY = "filesDirectory";
    private static final String RESOURCE_NAME = "resourceName";
    private static String userImplementation;
    private static String taskImplementation;
    private static String filesDirectory;
    private static String resourceName;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        userImplementation = servletContext.getInitParameter(USER_IMPLEMENTATION);

        taskImplementation = servletContext.getInitParameter(TASK_IMPLEMENTATION);

        filesDirectory = servletContext.getInitParameter(FILES_DIRECTORY);

        resourceName = servletContext.getInitParameter(RESOURCE_NAME);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        userImplementation = null;
        taskImplementation = null;
        filesDirectory = null;
        resourceName = null;
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

    public static String getResourceName() {
        return resourceName;
    }
}
