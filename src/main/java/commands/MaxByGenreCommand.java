package commands;

import management.Receiver;

public class MaxByGenreCommand implements Command {
    private final Receiver receiver;

    public MaxByGenreCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) {
        if (args.length > 0) {
            System.err.println("Only one argument is allowed");
            return;
        }
        receiver.maxByGenre();
    }

    @Override
    public String getName() {
        return "maxByGenreCommand";
    }
}
