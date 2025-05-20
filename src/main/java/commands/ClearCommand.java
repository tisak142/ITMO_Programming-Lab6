package commands;

import management.Receiver;

public class ClearCommand implements Command {
    public final Receiver receiver;

    public ClearCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        receiver.clearCollection();
        System.out.println("Collection successfully cleaned");

    }

    @Override
    public String getName() {
        return "Clear";
    }
}
