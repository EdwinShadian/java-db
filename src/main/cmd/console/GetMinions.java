package main.cmd.console;

import main.entity.Villain;
import main.exceptions.VillainIdNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class GetMinions {
    public static void main(String[] args) {
        try {
            Integer villainId = Integer.parseInt(args[0]);
            ArrayList<HashMap<String, Object>> minions = Villain.minions(villainId);

            if (minions.isEmpty()) {
                System.out.println("no minions");
                return;
            }

            Comparator<HashMap<String, Object>> mapComparator = (a, b) -> {
                String aName = String.valueOf(a.get("name"));
                String bName = String.valueOf(b.get("name"));

                if(aName == null && bName == null)
                    return 0;
                if(aName != null && bName == null)
                    return -1;
                if(aName == null)
                    return 1;
                return aName.compareTo(bName);
            };

            minions.sort(mapComparator);
            HashMap<String, Object> villain = Villain.findOrFail(villainId);
            if (villain != null) {
                System.out.println("Villain: " + villain.get("name"));
            }
            int i = 1;
            for (HashMap<String, Object> minion:minions) {
                System.out.println(i + ". " + minion.get("name") + " " + minion.get("age"));
                i++;
            }
        } catch (VillainIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
