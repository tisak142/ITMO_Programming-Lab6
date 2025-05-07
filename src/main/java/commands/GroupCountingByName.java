package commands;

import da.Receiver;

import java.util.HashMap;
import java.util.Map;

public class GroupCountingByName implements Command {
    private final Receiver receiver;

    public GroupCountingByName(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) {
        if (args.length > 0) {
            System.err.println("Only one argument is allowed");
            return;
        }
        HashMap<String, Integer> map = receiver.countByName();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Group " + entry.getKey() + " : " + value);
        }
    }

    @Override
    public String getName() {
        return "groupCountingByName";
    }
}
