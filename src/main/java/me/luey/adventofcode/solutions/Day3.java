package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 implements Solution {
    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        int score = 0;

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Set<String> first = Arrays.stream(line.substring(0, line.length() / 2).split(""))
                    .collect(Collectors.toUnmodifiableSet());

            String second = line.substring(line.length() / 2);
            Character repeatedChar = Arrays.stream(second.split(""))
                    .filter(c -> first.contains(c))
                    .map(c -> c.charAt(0))
                    .findFirst()
                    .orElseThrow();

            score += getPriority(repeatedChar);;
        }

        return String.valueOf(score);
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        int score = 0;
        while (bufferedReader.ready()) {
            Character repeatedChar = getRepeatedChar(bufferedReader);
            score += getPriority(repeatedChar);
        }
        return String.valueOf(score);
    }

    private static Character getRepeatedChar(BufferedReader bufferedReader) {
        String first = getLine(bufferedReader);
        String second = getLine(bufferedReader);
        String third = getLine(bufferedReader);

        return Arrays.stream(first.split(""))
                .filter(c -> second.contains(c))
                .filter(c -> third.contains(c))
                .map(c -> c.charAt(0))
                .findFirst()
                .orElseThrow();
    }

    private static String getLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getPriority(Character repeatedChar) {
        return repeatedChar.compareTo('a') >= 0 ? repeatedChar - 96 : repeatedChar - 38;
    }
}
