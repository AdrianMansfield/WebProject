package by.gsu.epamlab.beans.task;

import by.gsu.epamlab.beans.Jsonable;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Task implements Jsonable, Comparable<Task> {
    private int id;
    private String name;
    private String description;
    private Date date;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat(Constants.PRINT_DATE_FORMAT);
    private String fileName;

    public Task() {}

    public Task(int id, String name, String description, Date date, String fileName) {
        setId(id);
        setName(name);
        setDescription(description);
        setDate(date);
        setFileName(fileName);
    }

    public Task(int id, String name, String description, String date, String fileName) {
        setId(id);
        setName(name);
        setDescription(description);
        setDate(date);
        setFileName(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ParameterConstants.TASK_ID_PARAMETER, id);
        jsonObject.put(ParameterConstants.TASK_NAME_PARAMETER, name);
        return jsonObject;
    }

    @Override
    public int compareTo(Task o) {
        return id - o.id;
    }
}
