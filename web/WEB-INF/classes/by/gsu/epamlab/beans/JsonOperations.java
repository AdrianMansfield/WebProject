package by.gsu.epamlab.beans;

import org.json.simple.JSONArray;

import java.util.List;

public final class JsonOperations {

    public static  <T extends Jsonable> JSONArray getJsonArray(List<T> list) {
        JSONArray jsonArray = new JSONArray();

        for(T someObject : list) {
            jsonArray.add(someObject.toJson());
        }

        return jsonArray;
    }
}
