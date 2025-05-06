import commands.*;
import da.MusicBandConsoleCreator;
import da.Receiver;

import java.util.HashMap;

public class Invoker {
    private static final HashMap<String, Command> commands = new HashMap<>(); // Хранилище команд, где ключ — название команды, а значение — объект команды.
    private final Receiver receiver;
    private final MusicBandConsoleCreator musicBandConsoleCreator;


    public Invoker(Receiver receiver, MusicBandConsoleCreator musicBandConsoleCreator) {
        this.receiver = receiver;
        this.musicBandConsoleCreator = musicBandConsoleCreator;
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(receiver));
        commands.put("add", new AddCommand(receiver, musicBandConsoleCreator));
        commands.put("show", new ShowCommand(receiver));
        commands.put("update_id", new UpdateIdCommand(receiver, musicBandConsoleCreator));
        commands.put("remove_by_id", new RemoveByIdCommand(receiver));
//        commands.put("clear", new ClearCommand());
//        commands.put("save", new SaveCommand());
//        commands.put("execute_script", new ExecuteScriptCommand(this));
//        commands.put("exit", new ExitCommand());
//        commands.put("add_if_max", new AddIfMaxCommand());
//        commands.put("add_if_min", new AddIfMinCommand());
//        commands.put("shuffle", new ShuffleCommand());
//        commands.put("average_of_number_of_participants", new AverageParticipantsCommand());
//        commands.put("max_by_genre", new MaxByGenreCommand());
//        commands.put("group_counting_by_name", new CountingByNameCommand());

    }

    public void invoke(ParsedCommand parsedCommand) {
        if (parsedCommand != null) {
            Command command = commands.get(parsedCommand.getCommandName());
            if (command != null) {
                command.execute(parsedCommand.getArgs());
            } else {
                System.out.println("Неизвестная команда: " + parsedCommand.getCommandName());
            }
        }
    }
}
