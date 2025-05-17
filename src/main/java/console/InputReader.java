package console;

public interface InputReader {
    String readLine();

    default void close() {}
}