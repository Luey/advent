package me.luey.adventofcode;

import me.luey.adventofcode.day1.CalorieCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public abstract class Utils {

    public static String readFile(String fileName, Function<BufferedReader, String> fn) {
        String filePath = Objects.requireNonNull(CalorieCounter.class.getClassLoader().getResource(fileName)).getPath();

        try (FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return fn.apply(bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
