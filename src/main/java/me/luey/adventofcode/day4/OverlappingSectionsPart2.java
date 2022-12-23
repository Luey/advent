package me.luey.adventofcode.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class OverlappingSectionsPart2 {

    private record Section (int start, int end) {
        public static Section valueOf(String range) {
            List<Integer> collect = Arrays.stream(range.split("-"))
                    .map(num -> Integer.valueOf(num))
                    .toList();

            return new Section(collect.get(0), collect.get(1));
        }
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

    public int countOverlaps(Path inputPath) {
        int overlaps = 0;

        try (FileReader fileReader = new FileReader(inputPath.toFile());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] ranges = line.split(",");
                if (hasOverlap(Section.valueOf(ranges[0]), Section.valueOf(ranges[1]))) {
                    overlaps++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return overlaps;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputFilename = "sections.txt";
        Path inputPath = Path.of(OverlappingSectionsPart2.class.getClassLoader().getResource(inputFilename).getPath());

        int overlaps = new OverlappingSectionsPart2().countOverlaps(inputPath);

        System.out.println("Number of overlaps: " + overlaps);
    }
}
