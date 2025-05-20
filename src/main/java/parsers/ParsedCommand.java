package parsers;

public class ParsedCommand {
    private final String commandName;
    private final String[] args;

    public ParsedCommand(String commandName, String[] args) {
        this.commandName = commandName;
        this.args = args;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }
}
