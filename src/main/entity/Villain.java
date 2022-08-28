package main.entity;

import main.common.Model;
import main.exceptions.ModelNotFound;
import main.exceptions.VillainIdNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final public class Villain extends Model {
    public static String TABLE;
    public static List<String> PROPERTIES;
    public static final String MINIONS_VILLAINS_TABLE = "minions_villains";

    static {
        TABLE = "villains";
        PROPERTIES = Arrays.asList("id", "name", "evilness_factor");
    }

    public static ArrayList<HashMap<String, Object>> all() {
        return Model.all(TABLE, PROPERTIES);
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

    public static void destroy(Integer villainId) {
        Model.destroy(villainId, TABLE);
    }

    public static int unbind(Integer villainId) {
        return Model.unbind(villainId, "villain_id", MINIONS_VILLAINS_TABLE);
    }

    public static ArrayList<HashMap<String, Object>> minions(Integer villainId) throws VillainIdNotFoundException {
        ArrayList<HashMap<String, Object>> minions = new ArrayList<>();
        try (Statement statement = Villain.driver.connection.createStatement()) {
            HashMap<String, Object> villain = Villain.findOrFail(villainId);
            if ((villain != null ? villain.size() : 0) == 0) {
                throw new VillainIdNotFoundException();
            }
            ResultSet resultSet = statement.executeQuery(
                    "select * from "
                    + MINIONS_VILLAINS_TABLE
                    + " where villain_id="
                    + villainId);

            while (resultSet.next()) {
                HashMap<String, Object> minion = Minion.findOrFail(resultSet.getInt("minion_id"));
                minions.add(minion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return minions;
    }
}
