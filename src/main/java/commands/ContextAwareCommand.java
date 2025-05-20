package commands;

import console.InputReader;

public abstract class ContextAwareCommand implements Command {
    protected InputReader context;

    public void setContext(InputReader context) {
        this.context = context;
    }
}