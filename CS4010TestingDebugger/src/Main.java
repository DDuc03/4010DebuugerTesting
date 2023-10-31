import java.util.Scanner;

public class Main {

    // This command will ask you to enter the relevant content.
    public static final Scanner keyboard = new Scanner(System.in);

    // Main method
    public static void main(String[] args) {
        // Initialize a new class and use this new class to perform the relevant operations.
        Generator generator = new Generator(keyboard);
        // This new class will do mainloop() method.
        generator.mainLoop();
        // Close enter interface.
        keyboard.close();
    }
}
