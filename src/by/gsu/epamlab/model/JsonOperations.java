package by.gsu.epamlab.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collections;
import java.util.List;

public final class JsonOperations {

    private JsonOperations() {}

    public static  <T extends JsonAware> JSONArray<JSONObject> getJsonArray(List<T> list) {

        JSONArray<JSONObject> jsonArray = new JSONArray<>();

        for(T someObject : list) {
            jsonArray.add(someObject.toJson());
        }

        return jsonArray;
    }

    public static JSONArray<String> getStringJsonArray(String [] list) {
        JSONArray<String> jsonArray = new JSONArray<>();

        Collections.addAll(jsonArray, list);

        return jsonArray;
    }
}
