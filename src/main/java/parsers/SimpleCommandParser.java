package parsers;

import java.util.Arrays;

public class SimpleCommandParser implements CommandParser {

    @Override
    public ParsedCommand parseCommand(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) {
            return null;
        }
        String commandName = parts[0];
        String[] args;
        if (parts.length > 1) {
            args = Arrays.copyOfRange(parts, 1, parts.length);
        } else {
            args = new String[0]; // Создаем пустой массив аргументов
        }
        return new ParsedCommand(commandName, args);
    }

    @Override
    public String toString() {
        return "SimpleCommandParser";
    }
}
