package commands;

import management.Receiver;

public class AverageParticipantsCommand implements Command {
    private final Receiver receiver;

    public AverageParticipantsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        System.out.println("Average number of participants in groups is " + receiver.getAverageNumberOfParticipants());
    }

    @Override
    public String getName() {
        return "AverageOfNumberOfParticipants";
    }
}
