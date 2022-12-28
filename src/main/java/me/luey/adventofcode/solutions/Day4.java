package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day4 implements Solution {

    @Override
    public String part1(BufferedReader bufferedReader) throws IOException {
        int overlaps = 0;

        while (bufferedReader.ready()) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] ranges = line.split(",");
                if (subset(Section.valueOf(ranges[0]), Section.valueOf(ranges[1]))) {
                    overlaps++;
                }
            }
        }
        return String.valueOf(overlaps);
    }

    @Override
    public String part2(BufferedReader bufferedReader) throws IOException {
        int overlaps = 0;

        while (bufferedReader.ready()) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] ranges = line.split(",");
                if (hasOverlap(Section.valueOf(ranges[0]), Section.valueOf(ranges[1]))) {
                    overlaps++;
                }
            }
        }
        return String.valueOf(overlaps);
    }

    private record Section (int start, int end) {
        public static Section valueOf(String range) {
            List<Integer> collect = Arrays.stream(range.split("-"))
                    .map(num -> Integer.valueOf(num))
                    .toList();

            return new Section(collect.get(0), collect.get(1));
        }
    }

    private boolean subset(Section first, Section second) {
        if (first.start >= second.start && first.end <= second.end) {
            System.out.println(first + " is inside " + second);
            return true;
        }

        if (second.start >= first.start && second.end <= first.end) {
            System.out.println(second + " is inside " + first);
            return true;
        }

        System.out.println("No overlap between " + first + " and " + second);
        return false;
    }

    private boolean hasOverlap(Section first, Section second) {
        if (first.end < second.start) {
            return false;
        }

        if (second.end < first.start) {
            return false;
        }

        return true;
    }
}
