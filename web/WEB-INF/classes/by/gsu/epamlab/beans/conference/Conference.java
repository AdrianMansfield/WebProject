package by.gsu.epamlab.beans.conference;

import by.gsu.epamlab.beans.Jsonable;
import by.gsu.epamlab.constants.Constants;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conference implements Jsonable {
    private int id;
    private String name;
    private String description;
    private Date date;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat(Constants.PRINT_DATE_FORMAT);

    public Conference() {}

    public Conference(int id, String name, String description, Date date) {
        setId(id);
        setName(name);
        setDescription(description);
        setDate(date);
    }

    public Conference(int id, String name, String description, String date) {
        setId(id);
        setName(name);
        setDescription(description);
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
        jsonObject.put("id", id);
        jsonObject.put("taskName", name);
        return jsonObject;
    }
}
