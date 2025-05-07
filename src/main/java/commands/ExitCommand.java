package commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String... args) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "exit";
    }
}
