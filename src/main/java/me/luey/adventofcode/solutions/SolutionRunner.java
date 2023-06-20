package me.luey.adventofcode.solutions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class SolutionRunner {

    private Solution solution;
    private String inputFilename;

    public SolutionRunner(Solution solution, String inputFilename) {
        this.solution = solution;
        this.inputFilename = inputFilename;
    }

    public String part1() throws IOException {
        return solution.part1(createBufferedReader(inputFilename));
    }

    public String part2() throws IOException {
        return solution.part2(createBufferedReader(inputFilename));
    }

    private static BufferedReader createBufferedReader(String inputFilename) throws FileNotFoundException {
        String inputFilePath = Objects.requireNonNull(Solution.class.getClassLoader().getResource(inputFilename)).getPath();
        return new BufferedReader(new FileReader(inputFilePath));
    }
}
