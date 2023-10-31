import java.util.InputMismatchException;
import java.util.Scanner;

// G
public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;
    // Constructor overloading -> Allow a class to have multiple constructor names.
    // The first Constructor will use Scanner as parameter then assigns it to the keyboard static variable.
    public Generator(Scanner scanner) {
        keyboard = scanner;
    }
    // The second Constructor will use four bool parameter to initialize the alphabet object.
    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        printMenu();

        // Just initialize the start of choose model.
        String userOption = "-1";

        while (!userOption.equals("4")) {
            // The userOption will store the input string value.
            userOption = keyboard.next();
            // The execution of the exception thrown determines whether the input meets the requirements or not
            try {
                // Convert the string to int.
                int input = Integer.parseInt(userOption);
                if ( input < 1 || input > 4 ) {
                    throw new IllegalArgumentException("The number entered must be between 1 and 4!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter the valid number！");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            // Adjudge the input value.
            switch (userOption) {
                case "1" -> {
                    // If input value is 1 -> This program will run requuestPassword() method -> When this content is finished -> re-run printMenu()
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();
                default -> {
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                }
            }
        }
    }
    private void requestPassword() {
        // The default value is always false and the boolean value is changed based on the input.
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");
        // The whole while loop process, with other loops nested within it.
        // The Outermost loop:
        do {
            String input;
            correctParams = false;
            // One of the embedded loops
            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
                //One of the embedded loops will stop when the corresponding value are 'yes' or 'no', this process will continue to do while loop until it satisfies the requirements.
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            // If input is yes -> the public bool parameter IncludeLower will be assigned to true value.
            if (isInclude(input)) IncludeLower = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeUpper = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeNum = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeSym = true;

            //No Pool Selected -> One of these embedded loop results must be correct
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                correctParams = true;
            }
        // The Outermost loop will stop when correctParams is true.
        } while (correctParams);


        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();
        System.out.println(length);

        // Initialize the new target generator object according to the result obtained from the loop
        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        // Objects of type Password are implemented by calling method:GeneratePassword(length) based on the generator object.
        final Password password = generator.GeneratePassword(length);

        System.out.println("Your generated password -> " + password);
    }

    private Password GeneratePassword(int length) {

        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }


    // Transfer the bool value.
    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("yes")) {
            return true;
        } 
        else {
            return false;
        }
    }

    private void PasswordRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
