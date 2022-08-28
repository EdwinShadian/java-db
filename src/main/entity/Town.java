package main.entity;

import main.common.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Town extends Model {
    public static String TABLE;
    public static List<String> PROPERTIES;

    static {
        TABLE = "towns";
        PROPERTIES = Arrays.asList("id", "name", "country");
    }

    public static String select(String[] columns) {
        return Model.select(TABLE, columns);
    }

    public static ArrayList<HashMap<String, Object>> executeQuery(String query) {
        return Model.executeQuery(query, PROPERTIES);
    }

    public static void create(HashMap<String, Object> items, String query) {
        Model.create(items, query, PROPERTIES);
    }

    public static String insert() {
        return Model.insert(TABLE, PROPERTIES);
    }

}
