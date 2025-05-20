package commands;

import management.Receiver;

public class ShowCommand implements Command {
    private final Receiver receiver;

    public ShowCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        receiver.showBands();
    }

    @Override
    public String getName() {
        return "show";
    }
}
