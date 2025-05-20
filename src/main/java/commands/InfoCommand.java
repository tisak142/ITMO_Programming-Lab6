package commands;

import management.Receiver;

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
        String type = "Тип коллекции: " + receiver.getCollectionType();
        String length = "Размер коллекции: " + receiver.getNumBands();
        String time = "Дата инициализации коллекции: " + receiver.getDate();
        System.out.println(type);
        System.out.println(length);
        System.out.println(time);
    }

    @Override
    public String getName() {
        return "info";
    }
}
