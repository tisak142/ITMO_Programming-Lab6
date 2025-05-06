package commands;

import OrdinaryClasses.MusicBand;
import da.MusicBandConsoleCreator;
import da.Receiver;

public class RemoveByIdCommand implements Command {
    public final Receiver receiver;

    public RemoveByIdCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) {
        if (args.length > 1) {
            System.err.println("Only one argument is allowed");
            return;
        }
        try {
            Integer id = Integer.parseInt(args[0]);
            if (!receiver.containId(id)) {
                System.err.println("Error: element with id " + id + " does not exist in collection.");
                return;
            }
            receiver.removeById(id);
            System.out.println("The element with id " + id + " has successfully removed.");
        } catch (NumberFormatException e) {
            System.err.println("Error: invalid id entered. It should be an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: you should enter an id");
        }
    }

    @Override
    public String getName() {
        return "RemoveById";
    }
}
