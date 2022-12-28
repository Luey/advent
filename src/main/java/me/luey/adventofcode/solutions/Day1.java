package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Day1 implements Solution {
    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        return getElvesFromMostToLeastCalories(bufferedReader).first().toString();
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        TreeSet<Integer> caloriesPerElf = getElvesFromMostToLeastCalories(bufferedReader);
        Iterator<Integer> iterator = caloriesPerElf.iterator();
        return String.valueOf(iterator.next() + iterator.next() + iterator.next());
    }

    private static TreeSet<Integer> getElvesFromMostToLeastCalories(BufferedReader bufferedReader) throws IOException {
        TreeSet<Integer> caloriesPerElf = new TreeSet<>(Comparator.reverseOrder());
        int totalCaloriesForCurrentElf = 0;

        String line;
        while (true) {
            if (!((line = bufferedReader.readLine()) != null))
                break;

            // Elves are separated by an empty line
            if (line.isEmpty()) {
                caloriesPerElf.add(totalCaloriesForCurrentElf);
                totalCaloriesForCurrentElf = 0;
                continue;
            }

            totalCaloriesForCurrentElf += Integer.parseInt(line);
        }

        return caloriesPerElf;
    }
}
