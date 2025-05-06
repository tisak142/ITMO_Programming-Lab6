package commands;

import da.Receiver;

public class ShowCommand implements Command {
    private final Receiver receiver;

    public ShowCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String... args) {
        receiver.showBands();
    }

    @Override
    public String getName() {
        return "show";
    }
}
