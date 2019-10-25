package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleTest {

    private PrintStream out;

    @BeforeEach
    public void setUp() {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @Test
    void print() {
        Console.getInstance().print("test");

        var argumentCaptor = ArgumentCaptor.forClass(Object.class);
        verify(out).print(argumentCaptor.capture());
        assertEquals("test", argumentCaptor.getValue());
    }

    @Test
    void printLine() {
        Console.getInstance().printLine("test");

        var argumentCaptor = ArgumentCaptor.forClass(Object.class);
        verify(out).println(argumentCaptor.capture());
        assertEquals("test", argumentCaptor.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "", "abc", "   "})
    void choice(String input) {
        readInput(input);

        assertEquals(Optional.of(input).filter(s -> !s.isBlank()).map(s -> s.charAt(0)),
                Console.getInstance().choice(""));
    }

    private void readInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream((input + "\n").getBytes());
        System.setIn(in);
    }

    @ParameterizedTest
    @ValueSource(strings = {"string", "", "-1", "   "})
    void readString(String input) {
        readInput(input);

        assertEquals(input,
                Console.getInstance().readString("", "").get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "100", "-1", "0"})
    void readInt(String input) {
        readInput(input);

        assertEquals(Integer.parseInt(input),
                Console.getInstance().readInt("", ""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "100", "-1", "0"})
    void readIntWithHook(String input) {
        readInput(input);
        var sideEffect = new StringBuilder("i");

        int actual = Console.getInstance().readInt("", "",
                sideEffect::append);

        assertEquals(Integer.parseInt(input), actual);
        assertEquals("i"+input, sideEffect.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10/10/2010", "01/01/1999"})
    void readDate(String input) {
        readInput(input);

        assertEquals(LocalDate.parse(input, TypedIO.DF),
                Console.getInstance().readDate("", ""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10/10/2010", "01/01/1999"})
    void readDateWithHook(String input) {
        readInput(input);
        var sideEffect = new LocalDate[1];

        var actual = Console.getInstance().readDate("", "",
                d -> sideEffect[0] = d.plusDays(1));

        assertEquals(LocalDate.parse(input, TypedIO.DF), actual);
        assertEquals(actual.plusDays(1), sideEffect[0]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"10/10/2010 09:00:30", "01/01/1999 00:00:00", "01/01/1999 20:00:00"})
    void readDateTime(String input) {
        readInput(input);

        assertEquals(LocalDateTime.parse(input, TypedIO.TF),
                Console.getInstance().readDateTime("", ""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10/10/2010 09:00:30", "01/01/1999 00:00:00", "01/01/1999 20:00:00"})
    void readDateTimeWithHook(String input) {
        readInput(input);
        var sideEffect = new LocalDateTime[1];

        var actual = Console.getInstance().readDateTime("", "",
                d -> sideEffect[0] = d.plusSeconds(1));

        assertEquals(LocalDateTime.parse(input, TypedIO.TF), actual);
        assertEquals(actual.plusSeconds(1), sideEffect[0]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "100.0", "-1.0", "0.0"})
    void readDouble(String input) {
        readInput(input);

        assertEquals(Double.parseDouble(input),
                Console.getInstance().readDouble("", ""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.0", "100.0", "-1.0", "0.0"})
    void readDoubleWithHook(String input) {
        readInput(input);
        var sideEffect = new StringBuilder("i");

        double actual = Console.getInstance().readDouble("", "",
                sideEffect::append);

        assertEquals(Double.parseDouble(input), actual);
        assertEquals("i"+input, sideEffect.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123.1234567891011121314151617", "0.123456", "-10000000.0000000", "0"})
    void readBigDecimal(String input) {
        readInput(input);

        BigDecimal actual = Console.getInstance().readBigDecimal("", "");

        assertEquals(new BigDecimal(input), actual);
        assertEquals(input, actual.toPlainString());
    }
}