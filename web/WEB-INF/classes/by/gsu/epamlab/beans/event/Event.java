package by.gsu.epamlab.beans.event;

import by.gsu.epamlab.beans.Jsonable;
import org.json.simple.JSONObject;

public class Event implements Jsonable {
    private int id;
    private String name;
    private String time;

    public Event() {}

    public Event(int id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("eventName", name);
        jsonObject.put("eventTime", time);
        return jsonObject;
    }
}
