package parsers;

public class CommandParserFactory {
    public static CommandParser getParser(ParserTypes type) {
        switch (type) {
            case SIMPLE:
                return new SimpleCommandParser();
            default:
                throw new IllegalArgumentException("Unknown parser type: " + type);
        }
    }
}
