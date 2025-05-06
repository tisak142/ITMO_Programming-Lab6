package commands;

import OrdinaryClasses.MusicBand;
import da.MusicBandConsoleCreator;
import da.Receiver;

public class AddCommand implements Command {
    private final MusicBandConsoleCreator musicBandConsoleCreator;
    public final Receiver receiver;

    public AddCommand(Receiver receiver, MusicBandConsoleCreator musicBandConsoleCreator) {
        this.receiver = receiver;
        this.musicBandConsoleCreator = musicBandConsoleCreator;
    }

    @Override
    public void execute(String... args) {
        MusicBand newBand = musicBandConsoleCreator.createMusicBand();
        if (newBand != null) {
            receiver.add(newBand);
            System.out.println("Music band added successfully.");
        } else {
            System.out.println("Failed to add music band.");
        }
    }

    @Override
    public String getName() {
        return "Add";
    }
}
