package commands;

import da.Receiver;

public class ShuffleCommand implements Command {
    private final Receiver receiver;

    public ShuffleCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        receiver.shuffle();
        System.out.println("Collection has just been shuffled");
    }

    @Override
    public String getName() {
        return "shuffle";
    }
}
