package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Console implements TypedIO {
    private static final String CHOICE_ERROR = "Cannot read line. Try again.";
    private static final Console INSTANCE = new Console();

    @Override
    public void print(Object o) {
        System.out.print(o);
    }

    @Override
    public void printLine(Object o) {
        System.out.println(o);
    }

    public static Console getInstance() {
        return INSTANCE;
    }

    /**
     * Expects a string menu with choices represented as chars
     * and an error message in case of a wrong choice.
     * It will read the choice from System.in.
     *
     * @param menu
     * @param errorMessage
     * @return chosen option from the menu as char
     */
    public Optional<Character> choice(String menu, String errorMessage) {
        printLine(menu);
        try {
            return readString(menu, errorMessage)
                    .filter(s -> !s.isBlank())
                    .map(s -> s.charAt(0));
        } catch (Exception e) {
            printLine(errorMessage);
            return choice(menu);
        }
    }

    private BufferedReader reader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Expects a string menu with choices represented as chars.
     * It will read the choice from System.in.
     *
     * @param menu
     * @return chosen option from the menu as char
     */
    public Optional<Character> choice(String menu) {
        return choice(menu, CHOICE_ERROR);
    }

    @Override
    public Optional<String> readString(String prompt, String errMessage) {
        print(prompt);
        try {
            return Optional.ofNullable(reader().readLine());
        } catch (IOException e) {
            printLine(errMessage);
            return Optional.empty();
        }
    }
}
