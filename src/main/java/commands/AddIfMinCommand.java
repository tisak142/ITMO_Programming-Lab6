package commands;

import OrdinaryClasses.MusicBand;
import da.MusicBandConsoleCreator;
import da.Receiver;

public class AddIfMinCommand implements Command {
    private final Receiver receiver;
    private final MusicBandConsoleCreator musicBandConsoleCreator;

    public AddIfMinCommand(Receiver receiver, MusicBandConsoleCreator musicBandConsoleCreator) {
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
        receiver.addIfMin(newBand);
    }

    @Override
    public String getName() {
        return "addIfMin";
    }
}
