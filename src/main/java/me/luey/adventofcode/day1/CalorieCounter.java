package me.luey.adventofcode.day1;

import me.luey.adventofcode.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class CalorieCounter {

    public static void main(String[] args) {
        String caloriesSumForTopThreeElves = Utils.readFile("calories.txt", CalorieCounter::extracted);

        System.out.printf("The top three elves are carrying a total of %s calories", caloriesSumForTopThreeElves);
    }

    private static String extracted(BufferedReader bufferedReader) {
        TreeSet<Integer> caloriesPerElf = new TreeSet<>(Comparator.reverseOrder());

        int totalCaloriesForCurrentElf = 0;

        String line;
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null))
                    break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Elves are separated by an empty line
            if (line.isEmpty()) {
                caloriesPerElf.add(totalCaloriesForCurrentElf);
                totalCaloriesForCurrentElf = 0;
                continue;
            }

            totalCaloriesForCurrentElf += Integer.parseInt(line);
        }

        Iterator<Integer> iterator = caloriesPerElf.iterator();
        int caloriesSumForTopThreeElves = iterator.next() + iterator.next() + iterator.next();

        return String.valueOf(caloriesSumForTopThreeElves);
    }
}
