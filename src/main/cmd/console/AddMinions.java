package main.cmd.console;

import main.entity.Minion;
import main.entity.Town;
import main.entity.Villain;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMinions {
    public static void main(String[] args) {
        String minionName = args[0];
        Integer minionAge = Integer.parseInt(args[1]);
        String townName = args[2];
        String villainName = args[3];

        String townQuery = Town.select(new String[]{"*"}) + Town.where("name", townName);
        ArrayList<HashMap<String, Object>> town = Town.executeQuery(townQuery);
        if (town.isEmpty()) {
            HashMap<String, Object> townItems = new HashMap<>();
            townItems.put("name", townName);
            Town.create(townItems, Town.insert());
            town = Town.executeQuery(townQuery);
            System.out.println("Town named " + townName + " was added to database!");
        }

        String villainQuery = Villain.select(new String[]{"*"}) + Villain.where("name", villainName);
        ArrayList<HashMap<String, Object>> villain = Villain.executeQuery(villainQuery);
        if (villain.isEmpty()) {
            HashMap<String, Object> villainItems = new HashMap<>();
            villainItems.put("name", villainName);
            villainItems.put("evilness_factor", "evil");
            Villain.create(villainItems, Villain.insert());
            villain = Villain.executeQuery(villainQuery);
            System.out.println("Villain named " + villainName + " was added to database!");
        }

        Integer townId = ((Long) town.get(0).get("id")).intValue();

        HashMap<String, Object> minionItems = new HashMap<>();
        minionItems.put("name", minionName);
        minionItems.put("age", minionAge);
        minionItems.put("town_id", townId);
        Minion.create(minionItems, Minion.insert());

        String minionQuery = Minion.select(new String[]{"*"}) + Minion.where("name", minionName);
        ArrayList<HashMap<String, Object>> minion = Minion.executeQuery(minionQuery);
        Integer minionId = ((Long) minion.get(0).get("id")).intValue();

        Integer villainId = ((Long) villain.get(0).get("id")).intValue();

        HashMap<String, Object> bindings = new HashMap<>();
        bindings.put("villain_id", villainId);
        bindings.put("minion_id", minionId);

        Minion.bind(bindings);

        System.out.println("Minion named " + minionName + ", " + minionAge + " years old, was added to database!");
    }
}
