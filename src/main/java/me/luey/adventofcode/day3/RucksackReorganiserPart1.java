package me.luey.adventofcode.day3;

import me.luey.adventofcode.day2.RockPaperScissorsPart1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RucksackReorganiserPart1 {

    public static void main(String[] args) {
        String inputFilename = "rucksack-contents.txt";
        int total = new RucksackReorganiserPart1().reorganiseAllRucksacks(
                Objects.requireNonNull(RockPaperScissorsPart1.class.getClassLoader().getResource(inputFilename)).getPath());

        System.out.println("Total score: " + total);
    }

    public int reorganiseAllRucksacks(String filepath) {
        int score = 0;

        try (FileReader fileReader = new FileReader(filepath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return score;
    }

    private static int getPriority(Character repeatedChar) {
        return repeatedChar.compareTo('a') >= 0 ? repeatedChar - 96 : repeatedChar - 38;
    }
}
