package commands;

import javax.sound.midi.Receiver;
import java.io.Reader;

public class InfoCommand implements Command {
    private final Receiver receiver;

    public InfoCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
    }

    @Override
    public String getName() {
        return "info";
    }
}
