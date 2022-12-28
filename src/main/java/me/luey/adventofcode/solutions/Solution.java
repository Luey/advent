package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public interface Solution {
    record Results(String part1, String part2) {
    }

    String part1(BufferedReader bufferedReader) throws IOException;

    String part2(BufferedReader bufferedReader) throws IOException;

    static Results runSolution(String inputFilename, Solution solution) {
        try {
            return new Results(solution.part1(createBufferedReader(inputFilename)), solution.part2(createBufferedReader(inputFilename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static BufferedReader createBufferedReader(String inputFilename) throws FileNotFoundException {
        String inputFilePath = Objects.requireNonNull(Solution.class.getClassLoader().getResource(inputFilename)).getPath();
        return new BufferedReader(new FileReader(inputFilePath));
    }
}
