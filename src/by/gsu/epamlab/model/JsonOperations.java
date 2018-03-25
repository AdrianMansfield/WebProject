package by.gsu.epamlab.model;

import org.json.simple.JSONArray;

import java.util.List;

public final class JsonOperations {

    private JsonOperations() {}

    public static  <T extends Jsonable> JSONArray getJsonArray(List<T> list) {
        JSONArray jsonArray = new JSONArray();

        for(T someObject : list) {
            jsonArray.add(someObject.toJson());
        }

        return jsonArray;
    }
}
