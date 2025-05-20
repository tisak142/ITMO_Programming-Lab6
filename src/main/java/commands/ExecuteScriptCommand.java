package commands;

import console.ConsoleReader;
import console.InputReader;
import console.ScriptFileReader;
import da.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private final Receiver receiver;
    private final Invoker invoker;

    public ExecuteScriptCommand(Receiver receiver, Invoker invoker) {
        this.receiver = receiver;
        this.invoker = invoker;
    }

    @Override
    public void execute(String... args) {
        if (args.length != 1) {
            System.err.println("Укажите имя файла скрипта");
            return;
        }

        try (ScriptFileReader reader = new ScriptFileReader(args[0])) {
            invoker.setInputReader(reader);
            executeCommandsFromScript(reader);
        } catch (FileNotFoundException e) {
            System.err.println("Файл скрипта не найден: " + args[0]);
        } catch (Exception e) {
            System.err.println("Ошибка выполнения скрипта: " + e.getMessage());
        } finally {
            // Возвращаем консольный ввод
            invoker.setInputReader(new ConsoleReader(new Scanner(System.in)));
        }
    }

    private void executeCommandsFromScript(InputReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            CommandParser commandParser = CommandParserFactory.getParser(ParserTypes.SIMPLE);
            ParsedCommand cmd = commandParser.parseCommand(line);
            if (cmd != null) {
                invoker.invoke(cmd);
            }
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}