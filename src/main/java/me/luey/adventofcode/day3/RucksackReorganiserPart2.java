package me.luey.adventofcode.day3;

import me.luey.adventofcode.day2.RockPaperScissorsPart1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class RucksackReorganiserPart2 {

    public static void main(String[] args) {
        String inputFilename = "rucksack-contents.txt";
        int total = new RucksackReorganiserPart2().reorganiseAllRucksacks(
                Objects.requireNonNull(RockPaperScissorsPart1.class.getClassLoader().getResource(inputFilename)).getPath());

        System.out.println("Total score: " + total);
    }

    public int reorganiseAllRucksacks(String filepath) {
        int score = 0;

        try (FileReader fileReader = new FileReader(filepath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.ready()) {
                Character repeatedChar = getRepeatedChar(bufferedReader);
                score += getPriority(repeatedChar);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return score;
    }

    private static Character getRepeatedChar(BufferedReader bufferedReader) {
        return Arrays.stream(getLine(bufferedReader).split(""))
                .filter(c -> getLine(bufferedReader).contains(c))
                .filter(c -> getLine(bufferedReader).contains(c))
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
