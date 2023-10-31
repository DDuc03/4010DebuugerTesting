
public class Alphabet {
    // This Alphabet Class has Four Different attributes: UPPERCASE_LETTERS, LOWERCASE_LETTERS, NUMBERS, SYMBOLS
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "1234567890";
    public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";

    // Constructor -> If a new class is created,
    // the constructor can construct the content based on the values of the attributes that the alphabet class has.
    private final StringBuilder pool;

    // When this new Alphabet class is created, it will evaluate which attributes should be created by the next bool conditions:
    // uppercaseIncluded, lowercaseIncluded, numbersIncluded, specialCharactersIncluded.
    public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded, boolean specialCharactersIncluded) {
        // New constructor
        pool = new StringBuilder();
        // Relevant bool conditions:
        if (uppercaseIncluded) pool.append(UPPERCASE_LETTERS);
        if (lowercaseIncluded) pool.append(LOWERCASE_LETTERS);
        if (numbersIncluded) pool.append(NUMBERS);
        if (specialCharactersIncluded) pool.append(SYMBOLS);

    }
    // This method will help to obtain the constructor's content.
    public String getAlphabet() {
        return pool.toString();
    }
}