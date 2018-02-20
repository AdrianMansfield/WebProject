package by.gsu.epamlab.beans;

import by.gsu.epamlab.constants.Constants;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Task {
    private int id;
    private String name;
    private String department;
    private Date date;
    private List<Event> events;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat(Constants.PRINT_DATE_FORMAT);

    public Task() {}

    public Task(int id, String name, String department, Date date) {
        setId(id);
        setName(name);
        setDepartment(department);
        setDate(date);
    }

    public Task(int id, String name, String department, String date) {
        setId(id);
        setName(name);
        setDepartment(department);
        setDate(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDate() {
        return date;
    }

    public String getStringDate() {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        try {
            this.date = new Date(new SimpleDateFormat(Constants.USUAL_DATE_FORMAT)
                    .parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
