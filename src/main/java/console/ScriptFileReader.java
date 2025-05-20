package console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScriptFileReader implements InputReader, AutoCloseable {
    private final Scanner scanner;

    public ScriptFileReader(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    @Override
    public String readLine() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : null;
    }

    @Override
    public boolean isScriptMode() {
        return true;
    }

    @Override
    public void close() {
        scanner.close();
    }
}