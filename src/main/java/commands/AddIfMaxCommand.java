package commands;

import OrdinaryClasses.MusicBand;
import management.MusicBandConsoleCreator;
import management.Receiver;

public class AddIfMaxCommand implements Command {
    private final Receiver receiver;
    private final MusicBandConsoleCreator musicBandConsoleCreator;

    public AddIfMaxCommand(Receiver receiver, MusicBandConsoleCreator musicBandConsoleCreator) {
        this.receiver = receiver;
        this.musicBandConsoleCreator = musicBandConsoleCreator;
    }

    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        MusicBand newBand = musicBandConsoleCreator.createMusicBand();
        receiver.addIfMax(newBand);
    }

    @Override
    public String getName() {
        return "addIfMax";
    }
}
