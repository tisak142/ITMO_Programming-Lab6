package commands;

public class ExecuteScriptCommand implements Command {
    private final String filePath;

    public ExecuteScriptCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute(String... args) {


    }

    @Override
    public String getName() {
        return "executeScript";
    }
}