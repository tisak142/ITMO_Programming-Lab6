import OrdinaryClasses.*;
import console.ConsoleReader;
import console.InputReader;
import da.MusicBandConsoleCreator;
import da.Receiver;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private final InputReader inputReader;
    private final Invoker invoker;
    private final CommandParser commandParser;


    public Client(InputReader inputReader, Invoker invoker, CommandParser commandParser) {
        this.inputReader = inputReader;
        this.invoker = invoker;
        this.commandParser = commandParser;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicBandConsoleCreator musicBandConsoleCreator = new MusicBandConsoleCreator(scanner);
        Receiver receiver;
        FileLoader fileLoader = new FileLoader(System.getenv("ReadMusicBands"));
        ArrayList<MusicBand> musicBands = fileLoader.load();
        receiver = new Receiver(musicBands);
        InputReader consoleReader = new ConsoleReader(scanner);
        CommandParser commandParser = CommandParserFactory.getParser(ParserTypes.SIMPLE);
        Client client = new Client(consoleReader, new Invoker(receiver, musicBandConsoleCreator), commandParser);
        client.run();
    }

    public void run() {
        while(true) {
            System.out.print("✌ ");
            String line = inputReader.readLine();
            if (line.isEmpty()) {
                continue;
            } else {
                ParsedCommand parsedCommand = commandParser.parseCommand(line);
                if(parsedCommand != null) {
                    invoker.invoke(parsedCommand); // Передаем ParsedCommand
                } else {
                    System.out.println("unknown command, enter 'help' to see the list of commands");
                }
            }
        }
    }
}
