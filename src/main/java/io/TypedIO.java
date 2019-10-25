package io;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface TypedIO extends IO {

    DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter TF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    default LocalDate readDate() {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDate.parse(s, DF));
    }

    default LocalDate readDate(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDate.parse(s, DF));
    }

    default LocalDate readDate(String prompt, String errorMessage, Consumer<LocalDate> f) {
        return applyWithConsumer(this::readDate, f).apply(prompt, errorMessage);
    }

    default LocalDate readDate(Consumer<LocalDate> f) {
        return applyWithConsumer(this::readDate, f).apply(prompt, errorMessage);
    }

    default LocalDate readDate(String prompt, String errorMessage, String pattern) {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern)));
    }

    default LocalDate readDate(String pattern) {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern)));
    }

    default LocalDateTime readDateTime(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDateTime.parse(s, TF));
    }

    default LocalDateTime readDateTime() {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDateTime.parse(s, TF));
    }

    default LocalDateTime readDateTime(String prompt, String errorMessage, Consumer<LocalDateTime> f) {
        return applyWithConsumer(this::readDateTime, f).apply(prompt, errorMessage);
    }

    default LocalDateTime readDateTime(Consumer<LocalDateTime> f) {
        return applyWithConsumer(this::readDateTime, f).apply(prompt, errorMessage);
    }

    default LocalDateTime readDateTime(String prompt, String errorMessage, String pattern) {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDateTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
    }

    default LocalDateTime readDateTime(String pattern) {
        return readWithTypeFunction(prompt, errorMessage, s ->  LocalDateTime.parse(s, DateTimeFormatter.ofPattern(pattern)));
    }

    default int readInt(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Integer::parseInt);
    }

    default int readInt() {
        return readWithTypeFunction(prompt, errorMessage, Integer::parseInt);
    }

    default int readInt(String prompt, String errorMessage, Consumer<Integer> f) {
        return applyWithConsumer(this::readInt, f).apply(prompt, errorMessage);
    }

    default int readInt(Consumer<Integer> f) {
        return applyWithConsumer(this::readInt, f).apply(prompt, errorMessage);
    }

    default double readDouble(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Double::parseDouble);
    }

    default double readDouble() {
        return readWithTypeFunction(prompt, errorMessage, Double::parseDouble);
    }

    default double readDouble(String prompt, String errorMessage, Consumer<Double> f) {
        return applyWithConsumer(this::readDouble, f).apply(prompt, errorMessage);
    }

    default double readDouble(Consumer<Double> f) {
        return applyWithConsumer(this::readDouble, f).apply(prompt, errorMessage);
    }

    default long readLong(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Long::parseLong);
    }

    default long readLong() {
        return readWithTypeFunction(prompt, errorMessage, Long::parseLong);
    }

    default double readLong(String prompt, String errorMessage, Consumer<Long> f) {
        return applyWithConsumer(this::readLong, f).apply(prompt, errorMessage);
    }

    default double readLong(Consumer<Long> f) {
        return applyWithConsumer(this::readLong, f).apply(prompt, errorMessage);
    }

    default float readFloat(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Float::parseFloat);
    }

    default float readFloat() {
        return readWithTypeFunction(prompt, errorMessage, Float::parseFloat);
    }

    default float readFloat(String prompt, String errorMessage, Consumer<Float> f) {
        return applyWithConsumer(this::readFloat, f).apply(prompt, errorMessage);
    }

    default float readFloat(Consumer<Float> f) {
        return applyWithConsumer(this::readFloat, f).apply(prompt, errorMessage);
    }

    default byte readByte(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Byte::parseByte);
    }

    default byte readByte() {
        return readWithTypeFunction(prompt, errorMessage, Byte::parseByte);
    }

    default byte readByte(String prompt, String errorMessage, Consumer<Byte> f) {
        return applyWithConsumer(this::readByte, f).apply(prompt, errorMessage);
    }

    default byte readByte(Consumer<Byte> f) {
        return applyWithConsumer(this::readByte, f).apply(prompt, errorMessage);
    }

    default short readShort(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Short::parseShort);
    }

    default short readShort() {
        return readWithTypeFunction(prompt, errorMessage, Short::parseShort);
    }

    default short readShort(String prompt, String errorMessage, Consumer<Short> f) {
        return applyWithConsumer(this::readShort, f).apply(prompt, errorMessage);
    }

    default short readShort(Consumer<Short> f) {
        return applyWithConsumer(this::readShort, f).apply(prompt, errorMessage);
    }

    default boolean readBool(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, Boolean::parseBoolean);
    }

    default boolean readBool() {
        return readWithTypeFunction(prompt, errorMessage, Boolean::parseBoolean);
    }

    default boolean readBool(String prompt, String errorMessage, Consumer<Boolean> f) {
        return applyWithConsumer(this::readBool, f).apply(prompt, errorMessage);
    }

    default boolean readBool(Consumer<Boolean> f) {
        return applyWithConsumer(this::readBool, f).apply(prompt, errorMessage);
    }

    default BigInteger readBigInt(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, BigInteger::new);
    }

    default BigInteger readBigInt() {
        return readWithTypeFunction(prompt, errorMessage, BigInteger::new);
    }

    default BigInteger readBigInt(String prompt, String errorMessage, Consumer<BigInteger> f) {
        return applyWithConsumer(this::readBigInt, f).apply(prompt, errorMessage);
    }

    default BigInteger readBigInt(Consumer<BigInteger> f) {
        return applyWithConsumer(this::readBigInt, f).apply(prompt, errorMessage);
    }

    default BigDecimal readBigDecimal(String prompt, String errorMessage) {
        return readWithTypeFunction(prompt, errorMessage, BigDecimal::new);
    }

    default BigDecimal readBigDecimal() {
        return readWithTypeFunction(prompt, errorMessage, BigDecimal::new);
    }

    default BigDecimal readBigDecimal(String prompt, String errorMessage, Consumer<BigDecimal> f) {
        return applyWithConsumer(this::readBigDecimal, f).apply(prompt, errorMessage);
    }

    default BigDecimal readBigDecimal(Consumer<BigDecimal> f) {
        return applyWithConsumer(this::readBigDecimal, f).apply(prompt, errorMessage);
    }

    default <T> T readWithTypeFunction(String prompt, String errorMessage, Function<String, T> f) {
        try {
            return f.apply(readString(prompt, errorMessage).get());
        } catch (Exception e) {
            printLine(errorMessage);
            return readWithTypeFunction(prompt, errorMessage, f);
        }
    }

    default <T> BiFunction<String, String, T> applyWithConsumer(BiFunction<String, String, T> reader, Consumer<T> f) {
        return (String s1, String s2) -> {
            T val = reader.apply(s1, s2);
            f.accept(val);
            return val;
        };
    }

}