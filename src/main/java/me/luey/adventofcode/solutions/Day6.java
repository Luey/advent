package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;

public class Day6 implements Solution {

    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        return getIndexAfterFourUniqueCharacters(bufferedReader.readLine(), 4);
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        return getIndexAfterFourUniqueCharacters(bufferedReader.readLine(), 14);
    }

    private static String getIndexAfterFourUniqueCharacters(String input, int markerLength) {
        for (int i = markerLength; i < input.length(); i++) {
            long uniqueChars = input.substring(i - markerLength, i).chars().distinct().count();
            if (uniqueChars == markerLength) {
                return String.valueOf(i);
            }
        }

        throw new IllegalArgumentException("No marker detected");
    }
}
