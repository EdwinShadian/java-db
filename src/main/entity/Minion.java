package main.entity;

import main.common.Model;
import main.exceptions.ModelNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final public class Minion extends Model {
    public static String TABLE;
    public static List<String> PROPERTIES;
    public static final String MINIONS_VILLAINS_TABLE = "minions_villains";

    static {
        TABLE = "minions";
        PROPERTIES = Arrays.asList("id", "name", "age", "town_id");
    }

    public static HashMap<String, Object> findOrFail(Integer id) {
        try {
            return Model.findOrFail(id, TABLE, PROPERTIES);
        } catch (ModelNotFound e) {
            System.out.println(e.getMessage());
        }

        return null;
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

    public static void bind(HashMap<String, Object> bindings) {
        Model.bind(bindings, MINIONS_VILLAINS_TABLE);
    }
}
