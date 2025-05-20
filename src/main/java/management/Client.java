package management;

import OrdinaryClasses.*;
import console.ConsoleReader;
import console.InputReader;
import parsers.CommandParser;
import parsers.CommandParserFactory;
import parsers.ParsedCommand;
import parsers.ParserTypes;

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
        FileLoader fileLoader;
        ArrayList<MusicBand> musicBands;

        try {
            String filePath = System.getenv("ReadMusicBands");
            if (filePath == null || filePath.isEmpty()) {
                throw new IllegalArgumentException("Environment variable 'ReadMusicBands' is not set.");
            }
            fileLoader = new FileLoader(filePath);
            musicBands = fileLoader.load();
        } catch (Exception e) {
            System.err.println("Failed to load music bands from file: " + e.getMessage());
            return;
        }
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
                    invoker.invoke(parsedCommand); // Передаем parsers.ParsedCommand
                } else {
                    System.out.println("unknown command, enter 'help' to see the list of commands");
                }
            }
        }
    }
}
