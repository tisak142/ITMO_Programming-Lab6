package console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader implements InputReader {
    private final Scanner scanner;
    private boolean isScriptMode;

    public FileReader(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
        this.isScriptMode = true;
    }

    @Override
    public String readLine() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return null;
    }

    public boolean isScriptMode() {
        return isScriptMode;
    }

    public void close() {
        scanner.close();
    }
}