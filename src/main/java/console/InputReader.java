package console;

public interface InputReader {
    String readLine();
    default boolean isScriptMode() { return false; }
    default void close() {}
}