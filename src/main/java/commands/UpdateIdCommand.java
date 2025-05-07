package commands;

import OrdinaryClasses.MusicBand;
import da.MusicBandConsoleCreator;
import da.Receiver;

public class UpdateIdCommand implements Command {
    private final MusicBandConsoleCreator musicBandConsoleCreator;
    public final Receiver receiver;

    public UpdateIdCommand(Receiver receiver, MusicBandConsoleCreator musicBandConsoleCreator) {
        this.receiver = receiver;
        this.musicBandConsoleCreator = musicBandConsoleCreator;
    }

    @Override
    public void execute(String... args) {
        try {
            if (args.length > 1) {
                System.err.println("Only one argument is allowed");
                return;
            }
            int id = Integer.parseInt(args[0]);
            if (!receiver.containId(id)) {
                System.err.println("Error: element with id " + id + " does not exist in collection.");
                return;
            }
            MusicBand newBand = musicBandConsoleCreator.createMusicBand();
            receiver.updateId(id, newBand);
            System.out.println("The element with id " + id + " has successfully updated.");

        } catch (NumberFormatException e) {
            System.err.println("Error: invalid id entered. It should be an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: you should enter an id");
        }
    }

    @Override
    public String getName() {
        return "UpdateId";
    }
}
