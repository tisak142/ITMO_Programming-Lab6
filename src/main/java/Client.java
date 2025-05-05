import OrdinaryClasses.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Invoker invoker = new Invoker(in);
        while (true) {
            System.out.println("✌ ");
            String line = in.nextLine();
            invoker.invoke(line);
        }
    }
}
