package io;

import java.util.Optional;
import java.util.function.Consumer;

public interface IO {

    String prompt = "Please enter value: ";
    String errorMessage = "Can't read value!";

    void print(Object o);

    void printLine(Object o);

    Optional<String> readString(String prompt, String errorMessage);

    default Optional<String> readString() {
        return readString(prompt, errorMessage);
    }

    default Optional<String> readString(String prompt, String errorMessage, Consumer<String> f) {
        return readString(prompt, errorMessage)
                .map(s -> {
                    f.accept(s);
                    return s;
                });
    }
}
