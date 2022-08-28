package main.cmd.console;

import main.entity.Villain;

import java.util.HashMap;

public class DeleteVillain {
    public static void main(String[] args) {
        Integer villainId = Integer.parseInt(args[0]);

        HashMap<String, Object> villain = Villain.findOrFail(villainId);
        if (villain != null && villain.size() != 0) {
            String villainName = villain.get("name").toString();
            int minionsCount = Villain.unbind(villainId);
            Villain.destroy(villainId);
            System.out.println(villainName + " was deleted, " + minionsCount + " minions was released!");
        } else {
            System.out.println("No villain with such id!");
        }

    }
}
