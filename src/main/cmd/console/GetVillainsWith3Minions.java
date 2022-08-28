package main.cmd.console;

import main.entity.Villain;
import main.exceptions.VillainIdNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class GetVillainsWith3Minions {
    public static void main(String[] args) {
        ArrayList<HashMap<String, Object>> villains = Villain.all();

        if (!villains.isEmpty()) {
            HashMap<String, Integer> villainsWith3Minions = new HashMap<>();
            for (HashMap<String, Object> villain:villains) {
                int minionsCount = 0;
                try {
                    minionsCount = Villain.minions(Math.toIntExact((Long) villain.get("id"))).size();
                } catch (VillainIdNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                if (minionsCount > 3) {
                    villainsWith3Minions.put((String) villain.get("name"), minionsCount);
                }
            }

            villainsWith3Minions.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .forEach(e -> System.out.printf(e.getKey() + " - " + e.getValue() + "\n"));
        } else {
            System.out.println("No villains with more than 3 minions!");
        }
    }
}

